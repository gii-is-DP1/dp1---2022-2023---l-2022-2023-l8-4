package org.springframework.samples.petclinic.playergamedata;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PlayerGameDataServiceTest {
	
	@Autowired 
	private PlayerGameDataService playerGameDataService;
	
	@Test
	public void shouldWinPoint() {
		int gameId=1;
		int playerId=2;		
		Integer expectedPointsNumber= 32;
		this.playerGameDataService.winPoint(gameId, playerId);
		PlayerGameData actualData= this.playerGameDataService.getByIds(gameId, playerId);
		assertEquals(actualData.getPointsNumber(), expectedPointsNumber, "Point not added correctly");
	}
	
	@Test
	public void shouldSetWinner() {
		int gameId=1;
		int playerId=2;
		PlayerGameData firstData= this.playerGameDataService.getByIds(gameId, playerId);
		assertEquals(firstData.isWinner(), false);
		this.playerGameDataService.setWinner(gameId, playerId);
		PlayerGameData lastData= this.playerGameDataService.getByIds(gameId, playerId);
		assertEquals(lastData.isWinner(), true, "Winner has not been set correctly");
	}

}
