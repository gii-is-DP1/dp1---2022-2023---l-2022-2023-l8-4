package org.springframework.samples.petclinic.achievement;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.dao.DataAccessException;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameMode;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.game.GameState;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerController;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.statistics.Statistic;
import org.springframework.samples.petclinic.statistics.archivements.Achievement;
import org.springframework.samples.petclinic.statistics.archivements.AchievementController;
import org.springframework.samples.petclinic.statistics.archivements.AchievementService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AchievementController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
@MockBean(JpaMetamodelMappingContext.class)
public class AchievementControllerTest {
	
	@MockBean
	private PlayerService playerService;

	@MockBean
	private AchievementService achievementService;

	

	@Autowired
	private MockMvc mockMvc;

	private Integer playerId = 1;
	


		@BeforeEach
		private void setup() throws DataAccessException, NoSuchEntityException {
	        List<Achievement> achievements = new ArrayList<Achievement>();
	        Achievement achievement= new Achievement();
	        achievement.setId(playerId);
	        achievement.setName("Pasa");
	        achievements.add(achievement);
	        Mockito.when(achievementService.getAchievements(PageRequest.of(0, 5))).thenReturn(new PageImpl<>(achievements, PageRequest.of(0, 5), achievements.size()));
	        

		}
		
		@Test
		@WithMockUser(username = "admin1", password ="4dm1n", authorities = {"admin"})

		void shouldShowAchievements() throws Exception {
			mockMvc.perform(get("/statistics/achievements"))
			.andExpect(status().isOk())
			.andExpect(view().name("achievements/AchievementsListing"))
			.andExpect(model().attributeExists("achievements"))
			.andExpect(model().attribute("achievements", achievementService.getAchievements(PageRequest.of(0, 5)).getContent()))
			.andExpect(model().attribute("prev", 0));
		}
		
		
		
			
}
