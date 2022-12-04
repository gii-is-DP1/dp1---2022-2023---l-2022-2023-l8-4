package org.springframework.samples.petclinic.player;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
@TestInstance(Lifecycle.PER_CLASS)
public class PlayerServiceTest {

	@Autowired
	private PlayerService playerService;
	
	@Autowired
	private UserService userService;
	
	private int playerId = 1;
	
	@Test
	public void testsavePlayer() {
			Player player=new Player();
			player.setEmail("fausto@gmail.com");
			player.setProfilePicture(null);
			player.setBirthDate(LocalDate.now());
			
			User user=userService.findUser("pgmarc").get();
			player.setUser(user);
			
		
			playerService.savePlayer(player);
			Page<Player> players=playerService.getAllPlayers(PageRequest.of(0, 5));
			Integer idPlayerSave=(int) players.getTotalElements();
			Player playerSave = playerService.showPlayerById(idPlayerSave);
			
			assertEquals(playerSave.getId(), idPlayerSave);
			assertEquals(playerSave.getBirthDate(), LocalDate.now());
			assertEquals(playerSave.getRegisterDate(), LocalDate.now());
			assertEquals(playerSave.getModificationDate(), LocalDate.now());
	}
	
	@Test
	public void testFindPlayerByUsername() throws Exception {
			assertThrows(Exception.class,() -> playerService.getPlayerByUsername(""));
			Player player = playerService.getPlayerByUsername("pgmarc");
			assertEquals(player.getId(), Integer.valueOf(1));
			
	}
	
	@Test
	void shouldFindPlayerWithGames() {
		try {
			Player player = this.playerService.showPlayerById(playerId);
			Collection<Game> games = player.getPlayedGames();
			List<Game> listaGame = games.stream().collect(Collectors.toList());
			assertNotNull(games);
			assertEquals(3, games.size());
			assertEquals(listaGame.get(0).getGameCode(), Integer.valueOf(10));
			assertEquals(listaGame.get(1).getGameCode(), Integer.valueOf(12));
			assertEquals(listaGame.get(2).getGameCode(), Integer.valueOf(14));
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void shouldThrowException() {
		 NoSuchEntityException exception = assertThrows(NoSuchEntityException.class, () -> { 
			 	this.playerService.getPlayerByUsername("");
			 });

		    String expectedMessage = "Player not found";
		    String actualMessage = exception.getErrorMessage();
		   

		    assertTrue(expectedMessage.contains(actualMessage));
	}
	
//	@Test
//	void shouldFindPlayerWithAchievements() {
//		Player player = this.playerService.achievementsByUsername();
//		Collection<Game> game = player.getPlayedGames();
//		assertNotNull(game);
//		assertThat(game.size()).isEqualTo(3);
//	}
}
