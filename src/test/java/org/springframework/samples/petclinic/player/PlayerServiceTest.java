package org.springframework.samples.petclinic.player;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	
	
	@Test
	public void testsavePlayer() {
			Player player=new Player();
			player.setEmail("fausto@gmail.com");
			player.setProfilePicture(null);
			player.setBirthDate(LocalDate.now());
			
			User user=userService.findUser("pgmarc").get();
			player.setUser(user);
			
		
			playerService.savePlayer(player);
			Collection<Player> players=playerService.getAllPlayers();
			Integer idPlayerSave=players.size();
			Player playerSave = playerService.showPlayersById(idPlayerSave);
			
			assertEquals(playerSave.getId(), idPlayerSave);
			assertEquals(playerSave.getBirthDate(), LocalDate.now());
			assertEquals(playerSave.getRegisterDate(), LocalDate.now());
			assertEquals(playerSave.getLastLogin(), LocalDate.now());
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
			Player player = this.playerService.showPlayersById(playerId);
			Collection<Game> game = player.getPlayedGames();
			List<Game> listaGame = game.stream().toList();
			assertNotNull(game);
			assertThat(game.size()).isEqualTo(3);
			assertThat(listaGame.get(0).getGameCode()).isEqualTo(10);
			assertThat(listaGame.get(1).getGameCode()).isEqualTo(12);
			assertThat(listaGame.get(2).getGameCode()).isEqualTo(14);
		}catch (Exception e) {
			fail();
		}
	}
	
//	@Test
//	void shouldFindPlayerWithAchievements() {
//		Player player = this.playerService.achievementsByUsername();
//		Collection<Game> game = player.getPlayedGames();
//		assertNotNull(game);
//		assertThat(game.size()).isEqualTo(3);
//	}
}
