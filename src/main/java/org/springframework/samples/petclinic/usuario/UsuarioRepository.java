package org.springframework.samples.petclinic.usuario;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
	
	public Collection<Usuario> findAll();

	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.esAdministrador=0")
	public Collection<Usuario> findAllJugadores();
	
	@Query("SELECT usuario FROM Usuario usuario WHERE usuario.esAdministrador=1")
	public Collection<Usuario> findAllAdministradores();
	
	Usuario save(Usuario usuario);
}
