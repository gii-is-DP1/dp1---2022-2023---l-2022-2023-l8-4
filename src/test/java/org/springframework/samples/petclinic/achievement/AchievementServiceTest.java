package org.springframework.samples.petclinic.achievement;

import static org.junit.Assert.assertEquals;
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
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.statistics.archivements.Achievement;
import org.springframework.samples.petclinic.statistics.archivements.AchievementService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Service;
@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class AchievementServiceTest {
	@Autowired
	private AchievementService achievementService;
	
	@Test
	public void shouldThrowExceptionAchievements() {
		 NoSuchEntityException exception = assertThrows(NoSuchEntityException.class, () -> { 
			 	this.achievementService.getAchievementsByName("");
			 });

		    String expectedMessage = "Achievement not found";
		    String actualMessage = exception.getErrorMessage();
		   

		    assertTrue(expectedMessage.contains(actualMessage));
	}
	
	@Test
	public void testFindPlayerByName() throws Exception {
			assertThrows(Exception.class,() -> achievementService.getAchievementsByName(""));
			Achievement achievement = achievementService.getAchievementsByName("Gamer");
			assertEquals(achievement.getId(), Integer.valueOf(1));
			
	}
	
	

}
