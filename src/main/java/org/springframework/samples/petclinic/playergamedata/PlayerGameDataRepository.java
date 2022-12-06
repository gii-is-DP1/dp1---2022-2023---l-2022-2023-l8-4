package org.springframework.samples.petclinic.playergamedata;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerGameDataRepository extends CrudRepository<PlayerGameData,Integer>{

	public Collection<PlayerGameData> findAll();
	
	PlayerGameData save(PlayerGameData playerGameData);
	
	@Query("SELECT playerGameData FROM PlayerGameData playerGameData WHERE playerGameData.player.id=p and playerGameData.game.id = g")
	public PlayerGameData findByIds(@Param(value = "g") Integer gameId, @Param(value = "p") Integer playerId);
	
	
}
