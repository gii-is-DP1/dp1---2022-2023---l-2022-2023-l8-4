package org.springframework.samples.petclinic.player;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player,Integer> {
	
	public Collection<Player> findAll();
	
	@Query(value = "SELECT * FROM players  WHERE players.username=:username", nativeQuery = true)
	public Player findPlayerByUsername(@Param("username") String username);

	Player save(Player usuario);
	
}
