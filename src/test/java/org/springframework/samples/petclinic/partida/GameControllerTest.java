package org.springframework.samples.petclinic.partida;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

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
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameController;
import org.springframework.samples.petclinic.game.GameMode;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.game.GameState;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GameController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class GameControllerTest {
	
	@MockBean
	private GameService gameService;
	
	@MockBean
	private CardService cardService;
	
	@MockBean
	private PlayerService playerService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Integer gameId = 1;
	
	@BeforeEach
	private void setup() {
		Game game=new Game();
        game.setId(gameId);
        game.setDate(LocalDate.now());
        game.setGameCode(10);
        game.setGameMode(GameMode.ESTANDAR);
        game.setGameState(GameState.FINALIZED);
        Mockito.when(gameService.getGameById(gameId)).thenReturn(game);
        List<Game> games = new ArrayList<Game>();
        games.add(game);
        Mockito.when(gameService.getGames()).thenReturn(games);
	}
	
	
	@Test
	@WithMockUser(username = "carbersor", password ="123", authorities = {"admin"})
	void shouldShowGame() throws Exception {
		mockMvc.perform(get("/games/" + gameId))
		.andExpect(status().isOk())
		.andExpect(view().name("games/gameDetails"))
		.andExpect(model().attributeExists("game"))
		.andExpect(model().attribute("game", gameService.getGameById(gameId)));
	}
	
	@Test
	@WithMockUser(username = "admin1", password ="4dm1n", authorities = {"admin"})
	void shouldShowGameList() throws Exception {
		mockMvc.perform(get("/games/finalized"))
		.andExpect(status().isOk())
		.andExpect(view().name("games/listGamesFinalized"))
		.andExpect(model().attributeExists("games"))
		.andExpect(model().attribute("games", gameService.getGamesFinalized(PageRequest.of(0, 5))));
	}
	
	@Test
	@WithMockUser(username = "admin1", password ="4dm1n", authorities = {"admin"})
	void shouldCreateGame() throws Exception {
		mockMvc.perform(post("/games/new")
				.with(csrf())
				.param("date", "2022-11-04")
				.param("gameState", "0")
				.param("gameMode", "el foso")
				.param("gameCode", "40"))
		.andExpect(status().isOk())
		.andExpect(view().name("games/createGame"))
        .andExpect(model().attributeExists("game"));
	}	
}
