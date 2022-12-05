package org.springframework.samples.petclinic.game;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {

	List<Game> findAll();
	List<Game> findGamesByOrderByDateDesc();
	Optional<Game> findById(int id);
	Page<Game> findGamesByGameStateOrderByDateDesc(Pageable pageable, GameState gameState);
	Game findGameByGameCode(int gameCode);

}
