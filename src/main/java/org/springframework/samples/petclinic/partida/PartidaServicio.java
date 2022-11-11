package org.springframework.samples.petclinic.partida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidaServicio {

	private final PartidaRepositorio partidaRepositorio;
		
	@Autowired
	public PartidaServicio(PartidaRepositorio pr) {
		this.partidaRepositorio = pr;
	}
	
	@Transactional(readOnly = true)
	public List<Partida> getPartidas() throws DataAccessException {
		return this.partidaRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public Partida getPartidaPorId(int id) throws DataAccessException {
		return this.partidaRepositorio.findById(id).orElse(null);
	}
	
	@Transactional
	public void savePartida(Partida partida) throws DataAccessException {
		this.partidaRepositorio.save(partida);
	}
	
	@Transactional
	public void deletePartida(int id) throws DataAccessException {
		this.partidaRepositorio.deleteById(id);
	}	
}
