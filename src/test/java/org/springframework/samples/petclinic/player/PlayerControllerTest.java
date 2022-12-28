package org.springframework.samples.petclinic.player;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.statistics.archivements.AchievementService;

import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PlayerController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
@MockBean(JpaMetamodelMappingContext.class)
public class PlayerControllerTest {

	@MockBean
	private PlayerService playerService;

	@MockBean
	private AchievementService achievementService;

	@MockBean
	private GameService gameService;

	@Autowired
	private MockMvc mockMvc;

	private Integer playerId = 1;

	@BeforeEach
	private void setup() {
		Player player= new Player();
        player.setId(playerId);
        player.setRegisterDate(LocalDate.now());
        player.setModificationDate(LocalDate.now());
        player.setLastLogin(LocalDate.now());
        player.setEmail("plopezr2012@gmail.com");
    player.setBirthDate(LocalDate.of(2002, 12, 10));
    player.getProfilePicture();
    Mockito.when(playerService.showPlayerById(playerId)).thenReturn(player);
    List<Player> players = new ArrayList<Player>();
    players.add(player);
    Page<Player> pagePlayers= new PageImpl<Player>(players, PageRequest.of(0, 5), players.size());
    Mockito.when(playerService.getAllPlayers(null)).thenReturn(pagePlayers);
}

@Test
@WithMockUser(username = "admin1", password ="4dm1n", authorities = {"admin"})
void shouldShowPlayersAchievements() throws Exception {
	mockMvc.perform(get("/players/" + playerId + "/achievements"))
	.andExpect(status().isOk())
	.andExpect(view().name("achievements/AchievementsListing"))
	.andExpect(model().attributeExists("achievements"))
	.andExpect(model().attribute("achievements", achievementService.findAchievementByPlayerId(playerId)));
}

@Test
@WithMockUser(username = "admin1", password ="4dm1n", authorities = {"admin"})
void shouldShowPlayersData() throws Exception {
	mockMvc.perform(get("/players/data/" + playerId))
	.andExpect(status().isOk());
//	.andExpect(view().name("players/dataPlayer"))
//	.andExpect(model().attribute("player", playerService.showPlayerById(playerId)))
//	.andExpect(model().attribute("games", playerService.gamesByPlayerId(playerId, PageRequest.of(0, 5)).getContent()));
}
}


