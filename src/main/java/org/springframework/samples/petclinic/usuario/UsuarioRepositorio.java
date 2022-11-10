package org.springframework.samples.petclinic.usuario;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends CrudRepository<Usuario,Integer> {
	
	public Collection<Usuario> findAll();

	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.administrador=0")
	public Collection<Usuario> findAllJugadores();
	
	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.administrador=1")
	public Collection<Usuario> findAllAdministradores();
	
	Usuario save(Usuario usuario);
}
