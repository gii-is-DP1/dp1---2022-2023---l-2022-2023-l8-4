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
			Page<Player> players=playerService.getAllPlayers(PageRequest.of(0, 5));
			Integer idPlayerSave=(int) players.getTotalElements();
			Player playerSave = playerService.showPlayerById(idPlayerSave);
			
			assertEquals(playerSave.getId(), idPlayerSave);
			assertEquals(playerSave.getBirthDate(), LocalDate.now());
			assertEquals(playerSave.getRegisterDate(), LocalDate.now());
			assertEquals(playerSave.getModificationDate(), LocalDate.now());
	}
}
