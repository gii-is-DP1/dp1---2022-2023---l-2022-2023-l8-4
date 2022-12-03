package org.springframework.samples.petclinic.game;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerRepository;
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
		Game game = gameRepository.findById(id).orElse(null);

		if (game == null) {
			throw new NoSuchEntityException("404", "Game not found");
		}
		return game;
	}

	@Transactional(readOnly = true)
	public Collection<Player> getPlayersFromGame(int gameId) throws NoSuchEntityException, DataAccessException {
		Game game = getGameById(gameId);

		if (game == null) {
			throw new NoSuchEntityException("404", "Game not found");
		}

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
	
	public Game getGameByCode(int gameCode) {
		return this.gameRepository.findGameByGameCode(gameCode);
	}
	
}
