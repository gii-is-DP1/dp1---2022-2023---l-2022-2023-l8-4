package org.springframework.samples.petclinic.game;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

	private final GameRepository gameRepository;

	@Autowired
	public GameService(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
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
	public void deleteCardFromDeck(int gameId, int middleCardId){
		Optional<Game> game = gameRepository.findById(gameId);
		Collection<Card> cards= game.get().getCards();
		Card card = cards.stream().filter(c->c.getId().equals(middleCardId)).collect(Collectors.toList()).get(0);
		cards.remove(card);
		game.get().setCards(cards);
		saveGame(game.get());
	}

	@Transactional
	public Card selectRandomMiddleCard(int gameId){
		Optional<Game> game = gameRepository.findById(gameId);
		Collection<Card>deck =game.get().getCards();
		int random = (int) (Math.random() * deck.size());
		Card randomNode = deck.stream().collect(Collectors.toList()).get( random );
		return randomNode;
	}
}
