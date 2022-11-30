package org.springframework.samples.petclinic.game;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
	public List<Game> getGamesInProgress() throws DataAccessException {
		return this.gameRepository.findGamesByGameStateOrderByDateDesc(GameState.IN_PROGRESS);
	}

	@Transactional(readOnly = true)
	public List<Game> getGamesFinalized() throws DataAccessException {
		return this.gameRepository.findGamesByGameStateOrderByDateDesc(GameState.FINALIZED);
	}


	@Transactional(readOnly = true)
	public List<Game> getGamesByDateDesc() throws DataAccessException {
		return this.gameRepository.findGamesByOrderByDateDesc();
	}

	@Transactional(readOnly = true)
	public Game getGameById(int id) throws DataAccessException {
		return this.gameRepository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Collection<Player> getPlayersFromGame(int gameId) throws Exception {
		Game game = getGameById(gameId);

		if (game == null) {
			throw new Exception("Game not found");
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

    @Transactional
    public List<Game> gamesByPlayers(Integer id) {
        return (List<Game>) this.gameRepository.findGamesByPlayerId(id);
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
	
}
