package org.springframework.samples.petclinic.game;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {

	List<Game> findAll();
	Optional<Game> findById(int id);

    @Query( "SELECT g FROM Game g WHERE g.creatorId.id = :id" )
    List<Game> getGamesByPlayers(@Param("id") int playerId);
}
