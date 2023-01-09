package org.springframework.samples.petclinic.game;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTest {
	

	@Autowired
	private GameService gameService;
	
	@Autowired
	private UserService userService;
	
	@Test
	public void shouldGetPlayersFromGame(){
		try {
			List<Player> players = (List<Player>) this.gameService.getPlayersFromGame(1);
			String[] playersUsername = {"pgmarc","carbersor"};
			int numPlayersExpected = players.size();
			
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
	
	
	
	@Test
	public void shouldAddPlayerToGame() throws NoSuchEntityException {
		Game game = gameService.getGameById(1);
		Collection<Player> players = game.getPlayers();
		User user = userService.findUser("carzarrei").get();
		Player player = new Player();
		player.setUser(user);
		players.add(player);

		this.gameService.addPlayerToGame(player, game);
		
		Collection<Player> actualPlayers;
		try {
			actualPlayers = gameService.getPlayersFromGame(1);
			assertEquals(players.size(), actualPlayers.size());

		} catch (NoSuchEntityException e) {
			fail();
		}
	}
}
