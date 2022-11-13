package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;

@Service
public class GameService {

	private final GameRepository gameRepository;

	@Autowired
	public GameService(GameRepository pr) {
		this.gameRepository = pr;
	}

	@Transactional(readOnly = true)
	public List<Game> getGames() throws DataAccessException {
		return this.gameRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Game getGameById(int id) throws DataAccessException {
		return this.gameRepository.findById(id).orElse(null);
	}

	@Transactional
	public void saveGame(Game partida) throws DataAccessException {
		this.gameRepository.save(partida);
	}

	@Transactional
	public void deleteGame(int id) throws DataAccessException {
		this.gameRepository.deleteById(id);
	}

    @Transactional
    public List<Game> gamesByPlayers( Integer id ) throws DataAccessException {
        return this.gameRepository.getGamesByPlayers( id );
    }
}
