package org.springframework.samples.petclinic.player;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.statistics.archivements.Achievement;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
	
	public List<Player> findAll();
	
	Player save(Player usuario);
	
	@Query("SELECT games FROM Player player INNER JOIN player.playedGames games WHERE player.id= ?1")
	Page<Game> getGamesByPlayerId(Pageable pageable, Integer playerId);
	
	@Query("SELECT achievements FROM Player player INNER JOIN player.playersAchievement achievements WHERE player.id= :playerId")
	Page<Achievement> getAchievementsByPlayerId(Pageable pageable, @Param("playerId") Integer playerId);
	
	@Query(value = "SELECT * FROM players  WHERE players.username=:username", nativeQuery = true)
	public Player findPlayerByUsername(@Param("username") String username);
	
	@Query(value = "SELECT COUNT(*) FROM players", nativeQuery = true)
	public Integer getNumberOfPlayers();
}