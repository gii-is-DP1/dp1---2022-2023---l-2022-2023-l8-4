package org.springframework.samples.petclinic.game;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.playergamedata.PlayerGameData;
import org.springframework.samples.petclinic.playergamedata.PlayerGameDataService;
import org.springframework.samples.petclinic.statistics.Statistic;
import org.springframework.samples.petclinic.statistics.archivements.Achievement;
import org.springframework.samples.petclinic.statistics.archivements.AchievementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Service
public class GameService {

	private final GameRepository gameRepository;
	private final PlayerService playerService;
	private final AchievementService achievementService;
	private final PlayerGameDataService playerGameDataService;

	@Autowired
	public GameService(GameRepository gameRepository, AchievementService achievementService, PlayerGameDataService playerGameDataService, PlayerService playerService) {
		this.gameRepository = gameRepository;
		this.achievementService = achievementService;
		this.playerGameDataService = playerGameDataService;
		this.playerService = playerService;
	}

	@Transactional(readOnly = true)
	public List<Game> getGames() throws DataAccessException {
		return this.gameRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Page<Game> getGamesInProgress(Pageable pageable) throws DataAccessException {
		return this.gameRepository.findGamesByGameStateOrderByDateDesc(pageable, GameState.IN_PROGRESS);
	}


	@Transactional(readOnly = true)
	public Page<Game> getGamesFinalized(Pageable pageable) throws DataAccessException {
		return this.gameRepository.findGamesByGameStateOrderByDateDesc(pageable, GameState.FINALIZED);
	}


	@Transactional(readOnly = true)
	public List<Game> getGamesByDateDesc() throws DataAccessException {
		return this.gameRepository.findGamesByOrderByDateDesc();
	}

	@Transactional(readOnly = true)
	public Game getGameById(int id) throws NoSuchEntityException, DataAccessException {
		Game game = gameRepository.findById(id).orElseThrow(() -> new NoSuchEntityException("404", "Game not found"));

		return game;
	}

	@Transactional(readOnly = true)
	public Collection<Player> getPlayersFromGame(int gameId) throws NoSuchEntityException, DataAccessException {
		Game game = getGameById(gameId);


		return game.getPlayers();
	}

	@Transactional
	public void saveGame(Game game) throws DataAccessException {
		this.gameRepository.save(game);
	}

	@Transactional
	public void deleteGame(int gameid) throws DataAccessException {
		this.gameRepository.deleteById(gameid);
	}


	@Transactional(readOnly = true)
	public Game getGameByCode(int gameCode) {
		return this.gameRepository.findGameByGameCode(gameCode);
	}

	@Transactional
	public void addPlayerToGame(Player player, Game game) {
		game.addPlayer(player);
		saveGame(game);
	}

	@Transactional
	public void deleteCardFromDeck(int gameId, List<Card> deck){
		Optional<Game> game = gameRepository.findById(gameId);
		Collection<Card> cards= game.get().getCards();
		cards.remove( deck.get(0) );
		game.get().setCards(cards);
		saveGame(game.get());
	}
	//This method has to be called when a game is started before starting the gameplay
	@Transactional
	public void randomizeDeck(int gameId){
		Optional<Game> game = gameRepository.findById(gameId);
		List<Card>deck = new ArrayList<>(game.get().getCards());
		Collections.shuffle(deck);
        game.get().setCards( deck );
	}
	
	@Transactional
	public ModelAndView getResults(Game game, Player player, PlayerGameData data, String path) {
		ModelAndView res = new ModelAndView(path);
		List<Achievement> newAchievements = this.achievementService.checkNewAchievements(player, data);
		List<Achievement> playerAchievements = new ArrayList<Achievement>(player.getPlayersAchievement()); 
		playerAchievements.addAll(newAchievements);
		player.setPlayersAchievement(playerAchievements);
		this.addGameResultsToPlayer(game, player, data);
		playerService.savePlayer(player);
		
		res.addObject("newAchievements", newAchievements);
		res.addObject("points", data.getPointsNumber());
		res.addObject("game", game);
		res.addObject("winner", this.getWinner(game));
		return res;
	}
	
	@Transactional
	public Player getWinner(Game game) {
		Player winner = null;
		int max = 1000;
		for(Player player : game.getPlayers()) {
			int points = playerGameDataService.getByIds(game.getId(), player.getId()).getPointsNumber();
			if(points > max) {
				winner = player;
			}
		}
		return winner;
	}
	
	@Transactional
	public void addGameResultsToPlayer(Game game, Player player, PlayerGameData data) {
		Statistic stats= player.getStatistic();
		Player winner = this.getWinner(game);
		if(player.equals(winner)) {
			stats.setGamesWon(stats.getGamesWon()+1);
		} else {
			stats.setGamesLost(stats.getGamesLost()+1);
		}
		stats.setGamesPlayed(stats.getGamesPlayed()+1);
		stats.setTotalPoints(stats.getTotalPoints() + data.getPointsNumber());
		player.setStatistic(stats);
	}
	
}
