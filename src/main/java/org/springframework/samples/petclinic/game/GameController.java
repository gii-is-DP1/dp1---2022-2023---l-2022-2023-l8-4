package org.springframework.samples.petclinic.game;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/games")
public class GameController {
	
	private static final String GAME_DETAILS = "games/gameDetails";
	private static final String VIEW_CREATION_FORM = "games/createGame";
	private static final String VIEW_GAME_LIST = "games/listGames";
	private GameService gameService;
	private CardService cardService;
	private PlayerService playerService;
	
	@Autowired
	public GameController(GameService gameService, CardService cardService, PlayerService playerService) {
		this.gameService = gameService;
		this.cardService = cardService;
		this.playerService = playerService;
	}
	
	@GetMapping(value = "/new")
	public String iniciarFormulario(Map<String, Object> model) {
		Game game = new Game();
		game.setGameState(GameState.INITIATED);
		game.setGameMode(GameMode.ESTANDAR);
		game.setDate(LocalDate.now());
		List<GameMode> gameModes = Arrays.asList(GameMode.values());
		model.put("game", game);
		model.put("gameModes", gameModes);
		return VIEW_CREATION_FORM;
	}

	@PostMapping(value = "/new")
	public String procesarForlulario(@ModelAttribute("game") @Valid Game game, BindingResult result) throws DataAccessException, Exception {
		if (result.hasErrors()) {
			return VIEW_CREATION_FORM;
		}
		
		game.setDate(LocalDate.now());
		game.setGameState(GameState.INITIATED);
		game.setGameCode(ThreadLocalRandom.current().nextInt(0, 10000 + 1));
		game.setCards(cardService.getDeck());
		this.gameService.saveGame(game);
		return "redirect:/games/" + game.getId();
	}
	
	@GetMapping("/{gameId}")
	public ModelAndView initLobby(@PathVariable("gameId") int gameId) throws Exception {
		ModelAndView mav = new ModelAndView(GAME_DETAILS);
		mav.addObject("creator", true);
		
		Game game = this.gameService.getGameById(gameId);
		String currentUsername = "";
		try {
			User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			currentUsername = currentUser.getUsername();
		} catch(Exception e) {
			e.printStackTrace();
		}
		Collection<Player> listOfPlayers = this.gameService.getPlayersFromGame(gameId);
		listOfPlayers.add(playerService.getPlayerByUsername(currentUsername));
		game.setPlayers(listOfPlayers);
		mav.addObject("game", game);
		
		return mav;
	}
	
	@GetMapping(value = "/finalized")
	public String listFinalizedGames(Map<String, Object> model) {
		Collection<Game> games = gameService.getGamesFinalized();
		model.put("games", games);
		return VIEW_GAME_LIST;
		
	}
	
	@GetMapping(value = "/inProgress")
	public String listInProgressGames(Map<String, Object> model) {
		Collection<Game> games = gameService.getGamesInProgress();
		model.put("games", games);
		return VIEW_GAME_LIST;
		
	}
	
	@GetMapping("/join/{gameCode}")
	public ModelAndView joinGame(@PathVariable("gameCode") int gameCode) {
		ModelAndView mav = new ModelAndView(GAME_DETAILS);
		mav.addObject("game", this.gameService.getGameByCode(gameCode));
		mav.addObject("creator", false);
		return mav;
	}
	
}