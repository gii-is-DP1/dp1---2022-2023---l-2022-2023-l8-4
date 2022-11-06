package org.springframework.samples.petclinic.partida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaServicio {

	private final PartidaRepositorio partidaRepositorio;
	
	@Autowired
	public PartidaServicio(PartidaRepositorio pr) {
		this.partidaRepositorio = pr;
	}
	
	public List<Partida> getPartidas() {
		return partidaRepositorio.findAll();
	}
	
	public Partida getPartidaPorId(int id) {
		return this.partidaRepositorio.findById(id).orElse(null);
	}
	
	public void savePartida(Partida partida) {
		this.partidaRepositorio.save(partida);
	}
	
	public void deletePartida(int id) {
		this.partidaRepositorio.deleteById(id);
	}
	
}
