package org.springframework.samples.petclinic.usuario;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

	private UsuarioRepository usuarioRepositorio;
	
	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	@Transactional(readOnly=true)
	public Collection<Usuario> getAllUsuarios() {
		return usuarioRepositorio.findAll();
	}
	
	@Transactional(readOnly=true)
	public Collection<Usuario> getAllJugadores() {
		return usuarioRepositorio.findAllJugadores();
	}
	
	@Transactional(readOnly=true)
	public Collection<Usuario> getAllAdministradores() {
		return usuarioRepositorio.findAllAdministradores();
	}
	
	@Transactional(readOnly=true)
	public Usuario getUsuarioPorId(int id) {
		return this.usuarioRepositorio.findById(id).orElse(null);
	}
	
	@Transactional
	public void saveUsuario(Usuario usuario) throws DataAccessException {
		usuarioRepositorio.save(usuario);
	}
	
	@Transactional
	public void deleteUsuario(Usuario usuario) throws DataAccessException {
		usuarioRepositorio.delete(usuario);
	}
}
