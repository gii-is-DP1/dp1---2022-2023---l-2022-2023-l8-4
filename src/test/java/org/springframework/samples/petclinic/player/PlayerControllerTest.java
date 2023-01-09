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
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameMode;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.game.GameState;
import org.springframework.samples.petclinic.statistics.Statistic;
import org.springframework.samples.petclinic.statistics.archivements.Achievement;
import org.springframework.samples.petclinic.statistics.archivements.AchievementService;
import org.springframework.samples.petclinic.user.User;
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
	private String usernamePlayer="pgmarc";


		@BeforeEach
		private void setup() throws DataAccessException, NoSuchEntityException {
			Player player= new Player();
			User user = new User();
			user.setUsername("nuevo");
			user.setPassword("123");
			Statistic statistic = new Statistic();
			statistic.setTotalPoints(20);
			statistic.setGamesPlayed(1);
			statistic.setGamesWon(1);
			statistic.setGamesLost(0);
	        player.setId(playerId);
	        player.setUser(user);
	        player.setStatistic(statistic);
	        player.setRegisterDate(LocalDate.now());
	        player.setModificationDate(LocalDate.now());
	        player.setLastLogin(LocalDate.now());
	        player.setEmail("plopezr2012@gmail.com");
	        player.setBirthDate(LocalDate.of(2002, 12, 10));
	        player.getProfilePicture();
	        List<Player> players = new ArrayList<Player>();
	        List<Achievement> achievements = new ArrayList<Achievement>();
	        List<Game> games = new ArrayList<Game>();
	        Achievement achievement= new Achievement();
	        achievement.setId(playerId);
	        achievement.setName("Pasa");
	        Game game = new Game();
	        game.setDate(LocalDate.now());
	        game.setGameMode(GameMode.EL_FOSO);
	        game.setGameState(GameState.FINALIZED);
	        game.setPlayers(players);
	        game.setGameCode(15);
	        players.add(player);
	        achievements.add(achievement);
	        games.add(game);
	        player.setPlayersAchievement(achievements);
	        Page<Player> pagePlayers= new PageImpl<Player>(players, PageRequest.of(0, 5), players.size());
	        Mockito.when(playerService.gamesByPlayerId(playerId, PageRequest.of(0, 3))).thenReturn(new PageImpl<>(games, PageRequest.of(0, 3), games.size()));
	        Mockito.when(playerService.getAllPlayers(null)).thenReturn(pagePlayers);
	        Mockito.when(playerService.showAchievementsByPlayerId(playerId, PageRequest.of(0, 5))).thenReturn(new PageImpl<>(achievements, PageRequest.of(0, 5), achievements.size()));
	        Mockito.when(playerService.getPlayerById(playerId)).thenReturn(player);
	        Mockito.when(playerService.getPlayerByUsername(usernamePlayer)).thenReturn(player);
	        Mockito.when(playerService.showAchievementsByPlayerId(playerId, PageRequest.of(0, 5))).thenReturn(new PageImpl<>(achievements, PageRequest.of(0, 5), achievements.size()));
	    }
		
		
		@Test
		@WithMockUser(username = "pgmarc", password ="abc", authorities = {"admin"})
		void shouldShowPlayersAchievements() throws Exception {
			mockMvc.perform(get("/players/" + playerId + "/achievements"))
			.andExpect(status().isOk())
			.andExpect(view().name("achievements/AchievementsListing"))
			.andExpect(model().attributeExists("achievements"))
			.andExpect(model().attribute("achievements", playerService.showAchievementsByPlayerId(playerId, PageRequest.of(0, 5)).getContent()));
		}


		@Test
		@WithMockUser(username = "pgmarc", password ="abc", authorities = {"admin"})
		void shouldShowPlayersData() throws Exception {
			mockMvc.perform(get("/players/data/" + usernamePlayer ) )
			.andExpect(status().isOk())
			.andExpect(view().name("players/dataPlayer"))
			.andExpect(model().attributeExists("player"))
			.andExpect(model().attributeExists("games"))
			.andExpect(model().attribute("player", playerService.getPlayerById(playerId)))
			.andExpect(model().attribute("games", playerService.gamesByPlayerId(playerId, PageRequest.of(0, 3)).getContent()))
			.andExpect(model().attribute("username", "pgmarc"))
			.andExpect(model().attribute("prev", 0));
			
		}
		
		

	}


