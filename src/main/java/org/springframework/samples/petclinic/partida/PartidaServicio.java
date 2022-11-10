package org.springframework.samples.petclinic.partida;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PartidaServicio {

	private final PartidaRepositorio partidaRepositorio;
	
	private final UsuarioService usuarioServicio;
	
	@Autowired
	public PartidaServicio(PartidaRepositorio pr, UsuarioService us) {
		this.partidaRepositorio = pr;
		this.usuarioServicio = us;
	}
	
	@Transactional(readOnly = true)
	public List<Partida> getPartidas() throws DataAccessException {
		return this.partidaRepositorio.findAll();
	}
	
	@Transactional(readOnly = true)
	public Partida getPartidaPorId(int id) throws DataAccessException {
		return this.partidaRepositorio.findById(id).orElse(null);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void savePartida(Partida partida) throws DataAccessException, Exception {
		Usuario usuario = this.usuarioServicio.getUsuarioPorId(partida.getCreadorId().getId());
		if (usuario == null) {
			throw new Exception("Usuario no encontrado");
		}
		
		if (usuario.getAdministrador()) {
			throw new Exception("Un administrador no puede crear partidas");
		}
		
		this.partidaRepositorio.save(partida);
	}
	
	@Transactional
	public void deletePartida(int id) throws DataAccessException {
		this.partidaRepositorio.deleteById(id);
	}
	
}
