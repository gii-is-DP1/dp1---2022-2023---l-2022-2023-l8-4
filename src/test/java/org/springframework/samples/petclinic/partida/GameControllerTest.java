package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameController;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GameController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class GameControllerTest {
	
	@MockBean
	private GameService gameService;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Integer gameId = 1;
	
	@BeforeEach
	private void setup() {
		Game game=new Game();
        game.setId(gameId);
        Mockito.when(gameService.getGameById(gameId)).thenReturn(game);
        List<Game> games=new ArrayList<Game>();
        games.add(game);
        Mockito.when(gameService.getGames()).thenReturn(games);
	}
	
	@Test
	void test() throws Exception {
		shouldShowGame();
		shouldShowGameList();
		shouldCreateGame();		
	}
	
	
	void shouldShowGame() throws Exception{
		mockMvc.perform(get("/games/"+gameId))
		.andExpect(status().isOk())
		.andExpect(view().name("games/gameDetails"))
		.andExpect(model().attributeExists("game"))
		.andExpect(model().attribute("game", gameService.getGameById(gameId)));
	}
	
	void shouldShowGameList() throws Exception{
		mockMvc.perform(get("/games/"))
		.andExpect(status().isOk())
		.andExpect(view().name("games/listGames"))
		.andExpect(model().attributeExists("games"))
		.andExpect(model().attribute("games", gameService.getGames()));
	}
	
	void shouldCreateGame() throws Exception{
		mockMvc.perform(post("/games/create")
				.with(csrf())
				.param("date", "2022-11-04")
				.param("game_mode", "0"))
		.andExpect(status().isOk())
		.andExpect(view().name("games/createGame"))
        .andExpect(model().attributeExists("game"));
	}
	
	
	
}
