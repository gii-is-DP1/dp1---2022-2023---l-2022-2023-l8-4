package org.springframework.samples.petclinic.partida;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.jugador.Jugador;
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
	public List<Partida> getPartidasPorFechaDesc() throws DataAccessException {
		return this.partidaRepositorio.findPartidasByOrderByFechaDesc();
	}
	
	@Transactional(readOnly = true)
	public Partida getPartidaPorId(int id) throws DataAccessException {
		return this.partidaRepositorio.findById(id).orElse(null);
	}
	
	@Transactional(readOnly = true)
	public Collection<Jugador> getJugadoresPartida(int partidaId) throws Exception {
		Partida partida = getPartidaPorId(partidaId);
		
		if (partida == null) {
			throw new Exception("Partida no encontrada");
		}
		
		return partida.getJugadores();
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
