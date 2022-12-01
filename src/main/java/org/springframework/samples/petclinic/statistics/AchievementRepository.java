package org.springframework.samples.petclinic.statistics;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends CrudRepository<Achievement, Integer>{
    List<Achievement> findAll();
    
    @Query(value="SELECT achievement.id, achievement.name, achievement.description, achievement.threshold, achievement.percentage, achievement.acquire_date, achievement.trophy, achievement.badge_image FROM achievement "
			+ "JOIN players_achievements ON achievement.id=players_achievements.achievement_id "
			+ "JOIN players  ON players_achievements.player_id=players.id "
			+ "WHERE players.id= :id", nativeQuery= true)
	Collection<Achievement> findAchievementByPlayerId(@Param("id") int id);
    
   
}
