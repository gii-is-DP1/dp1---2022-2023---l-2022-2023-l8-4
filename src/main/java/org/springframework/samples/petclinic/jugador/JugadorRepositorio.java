package org.springframework.samples.petclinic.jugador;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepositorio extends CrudRepository<Jugador,Integer> {
	
	public Collection<Jugador> findAll();

	Jugador save(Jugador usuario);
}
