package org.springframework.samples.petclinic.player;


import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
	
	public Collection<Player> findAll();
	
	@Query(value = "SELECT * FROM players  WHERE players.username=:username", nativeQuery = true)
	public Player findPlayerByUsername(@Param("username") String username);

	Player save(Player usuario);
	
	
	@Query(value = "SELECT * FROM players  WHERE players.username=:username", nativeQuery = true)
	public Player findPlayerByUsername(@Param("username") String username);
}
