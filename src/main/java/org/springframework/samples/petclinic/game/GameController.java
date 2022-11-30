package org.springframework.samples.petclinic.game;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/games")
public class GameController {

	private static final String GAME_DETAILS = "games/gameDetails";
	private static final String VIEW_CREATION_FORM = "games/createGame";
	private static final String VIEW_GAME_LIST = "games/listGames";
	private static final String GAME_JOIN_VIEW = "games/joinGame";
	private GameService gameService;
	private CardService cardService;
	private PlayerService playerService;
	
	@Autowired
	public GameController(GameService gameService, CardService cardService, PlayerService playerService) {
		this.gameService = gameService;
		this.cardService = cardService;
		this.playerService = playerService;
	}
	
	private Game initGame() {
		Game game = new Game();
		game.setGameState(GameState.INITIATED);
		game.setGameMode(GameMode.ESTANDAR);
		game.setDate(LocalDate.now());
		return game;
	}
	
	@GetMapping(value = "/new")
	public String iniciarFormulario(Map<String, Object> model) {
		Game game = initGame();
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
		addCurrentPlayerToGame(game);
		this.gameService.saveGame(game);
		return "redirect:/games/" + game.getId();
	}

	@GetMapping("/{gameId}")
	public ModelAndView initLobby(@PathVariable("gameId") int gameId) throws Exception {
		ModelAndView mav = new ModelAndView(GAME_DETAILS);
		mav.addObject("creator", true);
		
		Game game = this.gameService.getGameById(gameId);
		
		mav.addObject("game", game);
		
		return mav;
	}

	@GetMapping(value = "/finalized")
	public String listarPartidasAcabadas(Map<String, Object> model, @RequestParam Map<String, Object> params) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Game> pageGamesFinalized= gameService.getGamesFinalized(pageRequest);
		
		int totalPages = pageGamesFinalized.getTotalPages();
		List<Integer> pages=new ArrayList<>();
		if(totalPages > 0) {
			pages= IntStream.rangeClosed(1, totalPages).boxed().toList();
		}
		
        model.put( "games", pageGamesFinalized.getContent());
        model.put( "pages", pages);
        model.put( "current", page + 1);
        model.put( "next", page + 2);
        model.put( "prev", page);
        model.put( "last", totalPages);
		return VIEW_GAME_LIST_FINALIZED;

	}

	@GetMapping(value = "/inProgress")
	public String listarPartidasEnProgreso(Map<String, Object> model, @RequestParam Map<String, Object> params) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Game> pageGamesInProgress= gameService.getGamesInProgress(pageRequest);
		
		int totalPages = pageGamesInProgress.getTotalPages();
		List<Integer> pages=new ArrayList<>();
		if(totalPages > 0) {
			pages= IntStream.rangeClosed(1, totalPages).boxed().toList();
		}
		
        model.put( "games", pageGamesInProgress.getContent());
        model.put( "pages", pages);
        model.put( "current", page + 1);
        model.put( "next", page + 2);
        model.put( "prev", page);
        model.put( "last", totalPages);
		return VIEW_GAME_LIST_IN_PROGRESS;
		
	}
	
	@GetMapping("/join")
	public ModelAndView joinGames() throws Exception {
		ModelAndView mav = new ModelAndView(GAME_JOIN_VIEW);
		mav.addObject("gameCode",0);
		return mav;
	}
	
	@PostMapping("/join")
	public String joinGame(@ModelAttribute("gameCode") int gameCode) throws Exception {
		Game game = gameService.getGameByCode(gameCode);
		addCurrentPlayerToGame(game);
		
		return "redirect:/games/join/"+game.getGameCode().toString();
	}
	
	@GetMapping("/join/{gameCode}")
	public ModelAndView joinGameCode(@PathVariable("gameCode") int gameCode) throws Exception {
		ModelAndView mav = new ModelAndView(GAME_DETAILS);
		mav.addObject("creator", false);
		
		Game game = this.gameService.getGameByCode(gameCode);
		mav.addObject("game",game);
		
		return mav;
	}
	
	private void addCurrentPlayerToGame(Game game) throws Exception {
		String currentUsername = "";
		try {
			Object currentUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (currentUser instanceof UserDetails) {
					currentUsername = ((UserDetails)currentUser).getUsername();
				} else {
					currentUsername = currentUser.toString();
				}
		} catch(Exception e) {
			e.printStackTrace();
		}
		Player player = playerService.getPlayerByUsername(currentUsername);
		game.addPlayer(player);
		gameService.saveGame(game);
	}



    @GetMapping(value = "/board")
    public String board(Map<String, Object> model) {
        return "games/board";

    }

}
