package org.springframework.samples.petclinic.playergamedata;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerGameDataRepository extends CrudRepository<PlayerGameData,Integer>{

	Collection<PlayerGameData> findAll();

	PlayerGameData save(PlayerGameData playerGameData);

	@Query("SELECT playerGameData FROM PlayerGameData playerGameData WHERE playerGameData.player.id=:p and playerGameData.game.id = :g")
	PlayerGameData findByIds(@Param(value = "g") Integer gameId, @Param(value = "p") Integer playerId);

    @Query( "SELECT pgd FROM PlayerGameData pgd WHERE pgd.game.id = :gameId")
    List<PlayerGameData> getDataByGameId(@Param( value = "gameId" ) Integer gameId);
}
