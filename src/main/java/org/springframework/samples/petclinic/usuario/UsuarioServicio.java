package org.springframework.samples.petclinic.usuario;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServicio {

	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	public UsuarioServicio(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}
	
	@Transactional(readOnly=true)
	public Collection<Usuario> getAllUsuarios(){
		return usuarioRepositorio.findAll();
	}
	
	@Transactional(readOnly=true)
	public Collection<Usuario> getAllJugadores(){
		return usuarioRepositorio.findAllJugadores();
	}
	
	@Transactional(readOnly=true)
	public Collection<Usuario> getAllAdministradores(){
		return usuarioRepositorio.findAllAdministradores();
	}
	
	@Transactional(readOnly = true)
	public Usuario mostrarUsuariosPorId(Long id) {
		Optional<Usuario> result= usuarioRepositorio.findById(id);
        return result.isPresent()?result.get():null;
		
	}
	
	@Transactional
	public void saveUsuario(Usuario usuario) throws DataAccessException {
		usuarioRepositorio.save(usuario);
	}
	
	@Transactional
	public void deleteUsuario(Long id) throws DataAccessException {
		usuarioRepositorio.deleteById(id);
	}
}
