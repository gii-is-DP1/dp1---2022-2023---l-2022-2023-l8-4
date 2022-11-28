package org.springframework.samples.petclinic.game;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

	List<Game> findAll();
	List<Game> findGamesByOrderByDateDesc();
	Optional<Game> findById(int id);
	List<Game> findGamesByGameStateOrderByDateDesc(GameState gameState);


}
