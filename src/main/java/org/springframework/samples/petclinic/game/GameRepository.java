package org.springframework.samples.petclinic.game;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

	List<Game> findAll();
	List<Game> findGamesByOrderByDateDesc();
	Optional<Game> findById(int id);
	Page<Game> findGamesByGameStateOrderByDateDesc(Pageable pageable, GameState gameState);
	Game findGameByGameCode(int gameCode);

}
