package org.springframework.samples.petclinic.player;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player,Integer> {
	
	public Collection<Player> findAll();

	Player save(Player usuario);
}
