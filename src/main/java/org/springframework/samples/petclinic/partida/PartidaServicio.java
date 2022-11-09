package org.springframework.samples.petclinic.partida;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidaServicio {

	private final PartidaRepositorio partidaRepositorio;
	
	private final UsuarioRepository usuarioRepositorio;
	
	@Autowired
	public PartidaServicio(PartidaRepositorio pr, UsuarioRepository ur) {
		this.partidaRepositorio = pr;
		this.usuarioRepositorio = ur;
	}
	
	@Transactional(readOnly = true)
	public List<Partida> getPartidas() throws DataAccessException {
		return partidaRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public Partida getPartidaPorId(int id) throws DataAccessException {
		return this.partidaRepositorio.findById(id).orElse(null);
	}
	
	@Transactional
	public void savePartida(Partida partida) throws DataAccessException, Exception {
		Optional<Usuario> usuario = this.usuarioRepositorio.findById(partida.getCreadorPartida().getId());
		if (usuario.isEmpty()) {
			throw new Exception("Usuario no encontrado");
		}
		
		if (usuario.get().isEsAdministrador()) {
			throw new Exception("Un administrador no puede crear partidas");
		}
		
		this.partidaRepositorio.save(partida);
	}
	
	@Transactional
	public void deletePartida(int id) throws DataAccessException {
		this.partidaRepositorio.deleteById(id);
	}
	
}
