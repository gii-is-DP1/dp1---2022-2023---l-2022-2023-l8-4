package org.springframework.samples.petclinic.partida;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Service;



@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTest {
	

	@Autowired
	private GameService gameService;
	
	@Test
	public void shouldGetPlayersFromGame(){
		try {
			List<Player> players = new ArrayList<Player>(this.gameService.getPlayersFromGame(1));
			String[] playersUsername = {"pgmarc","carbersor"};
			int numPlayersExpected = 2;
			
			assertEquals(numPlayersExpected, players.size());
			assertEquals(playersUsername[0], players.get(0).getUser().getUsername());
			assertEquals(playersUsername[1], players.get(1).getUser().getUsername());
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void shouldThrowException() {
		 NoSuchEntityException exception = assertThrows(NoSuchEntityException.class, () -> { 
			 	this.gameService.getPlayersFromGame(-1);
			 });

		    String expectedMessage = "Game not found";
		    String actualMessage = exception.getErrorMessage();
		   

		    assertTrue(expectedMessage.contains(actualMessage));
	}
}
