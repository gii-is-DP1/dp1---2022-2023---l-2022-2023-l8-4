package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GameService {

	private final GameRepository partidaRepositorio;
		
	@Autowired
	public GameService(GameRepository pr) {
		this.partidaRepositorio = pr;
	}
	
	@Transactional(readOnly = true)
	public List<Game> getGames() throws DataAccessException {
		return this.partidaRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public Game getGameById(int id) throws DataAccessException {
		return this.partidaRepositorio.findById(id).orElse(null);
	}
	
	@Transactional
	public void saveGame(Game partida) throws DataAccessException {
		this.partidaRepositorio.save(partida);
	}
	
	@Transactional
	public void deleteGame(int id) throws DataAccessException {
		this.partidaRepositorio.deleteById(id);
	}	
}
