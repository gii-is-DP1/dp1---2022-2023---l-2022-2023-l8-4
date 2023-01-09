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

import org.springframework.samples.petclinic.playergamedata.PlayerGameData;

import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.playergamedata.PlayerGameDataService;
import org.springframework.samples.petclinic.statistics.Statistic;
import org.springframework.samples.petclinic.statistics.StatisticService;
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
	private final StatisticService statisticService;

	@Autowired
	public GameService(GameRepository gameRepository, AchievementService achievementService, 
			PlayerGameDataService playerGameDataService, PlayerService playerService,
			StatisticService statisticService) {
		
		this.gameRepository = gameRepository;
		this.achievementService = achievementService;
		this.playerGameDataService = playerGameDataService;
		this.playerService = playerService;
		this.statisticService= statisticService;
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
	public void deleteCardFromDeckEstandar(int gameId, List<Card> deck){
		Optional<Game> game = gameRepository.findById(gameId);
		Collection<Card> cards= game.get().getCards();
		cards.remove( deck.get(0) );
		game.get().setCards(cards);
		saveGame(game.get());
	}
	
	@Transactional
	public void deleteCardsFromDeckElFoso(int gameId, List<Card> deck){
		Optional<Game> game = gameRepository.findById(gameId);
		game.get().setCards(deck);
		saveGame(game.get());
	}
	
	@Transactional
	public void changeGameCardElFoso(int gameId,PlayerGameData pgd) {
		Optional<Game> game = gameRepository.findById(gameId);
		List<Card> middleCards= game.get().getCards().stream().collect(Collectors.toList());
		Card playerCard= pgd.getActualCard();
		middleCards.add(0,playerCard);
		game.get().setCards(middleCards);
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
		res.addObject("newAchievements", this.achievementService.checkPlayerNewAchievements(player, data));
		res.addObject("points", data.getPointsNumber());
		res.addObject("game", game);
		res.addObject("winner", this.getWinner(game));
		return res;
	}
	
	@Transactional
	public void saveResults(Game game, Player player, PlayerGameData data) {
		this.savePlayerResults(game, player, data);
		this.saveGameResults(game);
	}
	
	@Transactional
	private void saveGameResults(Game game) {
		game.setGameState(GameState.FINALIZED);
		this.saveGame(game);
	}
	
	@Transactional
	private void savePlayerResults(Game game, Player player, PlayerGameData data) {
		updatePlayerAchievements(player, data);
		this.addGameResultsToPlayerStadistics(game, player, data);
		playerService.savePlayer(player);
	}
	
	@Transactional
	private void updatePlayerAchievements(Player player, PlayerGameData data) {
		List<Achievement> newAchievements = this.achievementService.checkPlayerNewAchievements(player, data);
		List<Achievement> playerAchievements = new ArrayList<Achievement>(player.getPlayersAchievement()); 
		playerAchievements.addAll(newAchievements);
		player.setPlayersAchievement(playerAchievements);
	}
	
	@Transactional
	private void addGameResultsToPlayerStadistics(Game game, Player player, PlayerGameData data) {
		Statistic statistics= player.getStatistic();
		Player winner = this.getWinner(game);
		statisticService.updatePlayerStadistics(player, data, statistics, winner);
	}
	
	@Transactional
	private Player getWinner(Game game) {
		Player winner = null;
		int maxPoints = 0;
		for(Player player : game.getPlayers()) {
			int pointsPlayer = playerGameDataService.getByIds(game.getId(), player.getId()).getPointsNumber();
			if(pointsPlayer > maxPoints) {
				winner = player;
				maxPoints = pointsPlayer;
			}
		}
		return winner;
	}
	
}
