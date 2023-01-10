package org.springframework.samples.petclinic.game;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.playergamedata.PlayerGameData;
import org.springframework.samples.petclinic.playergamedata.PlayerGameDataService;
import org.springframework.samples.petclinic.statistics.Statistic;
import org.springframework.samples.petclinic.statistics.archivements.Achievement;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = GameController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
@MockBean(JpaMetamodelMappingContext.class)
public class GameControllerTest {
	
	@MockBean
	private GameService gameService;
	
	@MockBean
	private CardService cardService;
	
	@MockBean
	private PlayerService playerService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PlayerGameDataService playerDataService;
	
	private Integer gameId = 1;
	private Integer playerId = 1;
	
	@BeforeEach
	private void setup() throws DataAccessException, NoSuchEntityException {
		Game game= new Game();
		List<Game> games = new ArrayList<Game>();
        this.setupGame(game, games);
        
        Player player = new Player();
        this.setupPlayer(player);
        game.addPlayer(player);
        
        PlayerGameData data= new PlayerGameData();
        this.setupPlayerGameData(data, game, player);
        
        ModelAndView results = new ModelAndView();
        this.setupModelAndView(results, player, game);
        
        Mockito.when(gameService.getGameById(gameId)).thenReturn(game);
        Mockito.when(gameService.getGamesFinalized(PageRequest.of(0, 5))).thenReturn(new PageImpl<>(games, PageRequest.of(0, 5), games.size()));
        Mockito.when(gameService.getGamesInProgress(PageRequest.of(0, 5))).thenReturn(new PageImpl<>(games, PageRequest.of(0, 5), games.size()));
        Mockito.when(gameService.getResults(game, player, data, "games/results")).thenReturn(results);
        Mockito.when(gameService.saveResults(game, player, data)).thenCallRealMethod();
        Mockito.when(playerService.getPlayerById(player.getId())).thenReturn(player);
        Mockito.when(playerDataService.getByIds(game.getId(), player.getId())).thenReturn(data);
	}
	
	private void setupGame(Game game, List<Game> games) {
		game.setId(gameId);
        game.setGameCode(10);
        game.setGameMode(GameMode.ESTANDAR);
        game.setGameState(GameState.FINALIZED);
        Card card = new Card();
        List<Card> deck = new ArrayList<Card>();
        deck.add(card);
        game.setCards(deck);
        games.add(game);
	}
	
	private void setupPlayer(Player player) {
		player.setId(playerId);
		User user = new User();
		user.setUsername("pgmarc");
		user.setPassword("abc");
		player.setUser(user);
        Statistic stats = new Statistic();
        stats.setGamesLost(10);
        stats.setGamesWon(10);
        stats.setGamesPlayed(20);
        stats.setTotalPoints(50);
        player.setStatistic(stats);
	}
	
	private void setupPlayerGameData(PlayerGameData data, Game game, Player player) {
		List<Card> deck =  game.getCards().stream().collect(Collectors.toList());
		playerDataService.initGameParamsEstandar(deck.get(0).getId(), data, player, game);
        gameService.deleteCardFromDeckEstandar(gameId, deck);
	}
	
	private void setupModelAndView(ModelAndView mav, Player player, Game game) {
		mav.addObject("winner", player);
		mav.addObject("points", 50);
		mav.addObject("game", game);
		List<Achievement> newAchievements = new ArrayList<Achievement>();
		Achievement achievement = new Achievement();
		achievement.setId(5);
		newAchievements.add(achievement);
		mav.addObject("newAchievements", newAchievements);
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

	void shouldShowGameListFinalized() throws Exception {
		mockMvc.perform(get("/games/finalized"))
		.andExpect(status().isOk())
		.andExpect(view().name("games/listGamesFinalized"))
		.andExpect(model().attributeExists("games"))
		.andExpect(model().attribute("games", gameService.getGamesFinalized(PageRequest.of(0, 5)).getContent()));
	}

	@Test
	@WithMockUser(username = "admin1", password ="4dm1n", authorities = {"admin"})

	void shouldShowGameListInProgress() throws Exception {
		mockMvc.perform(get("/games/inProgress"))
		.andExpect(status().isOk())
		.andExpect(view().name("games/listGamesInProgress"))
		.andExpect(model().attributeExists("games"))
		.andExpect(model().attribute("games", gameService.getGamesInProgress(PageRequest.of(0, 5)).getContent()));
	}
	

	
	@Test
	@WithMockUser(username = "pgmarc", password ="abc", authorities = {"admin"})
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
	
	@Test
	@WithMockUser(username = "pgmarc", password ="abc", authorities = {"admin"})
	void shouldShowResults() throws Exception {
		
		mockMvc.perform(get("/games/results/"+gameId+"/"+playerId))
		.andExpect(status().isOk())
        .andExpect(model().attributeExists("game"))
        .andExpect(model().attributeExists("newAchievements"))
        .andExpect(model().attributeExists("winner"))
        .andExpect(model().attribute("points", 50))
        .andExpect(model().attribute("show", true));
	}
	
	@Test
	@WithMockUser(username = "pgmarc", password ="abc", authorities = {"admin"})
	void shouldSaveResults() throws Exception {
		
		Player player = this.playerService.getPlayerById(playerId);
		mockMvc.perform(post("/games/results/"+gameId+"/"+playerId)
				.with(csrf()))
		.andExpect(status().isOk());		
	}
	
}
