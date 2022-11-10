package org.springframework.samples.petclinic.partida;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaRepositorio extends CrudRepository<Partida, Integer> {
	
	List<Partida> findAll();
	Optional<Partida> findById(int id);
}
