package org.springframework.samples.petclinic.statistics;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.statistics.archivements.Achievement;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends CrudRepository<Statistic, Integer> {

    @Query( value = "SELECT s.gamesWon FROM Statistic s WHERE s.player.id = :id " )
    Integer getWonGamesByPlayer(@Param("id") int id);

    @Query( value = "SELECT s.gamesLost FROM Statistic s WHERE s.player.id = :id " )
    Integer getLostGamesByPlayer(@Param("id") int id);

    @Query( value = "SELECT s.gamesPlayed FROM Statistic s WHERE s.player.id = :id " )
    Integer getPlayedGamesByPlayer(@Param("id") int id);
}
