package org.springframework.samples.petclinic.game;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/games")
public class GameController {
	
	private static final String WELCOME = "welcome";
	private static final String VIEW_CREATION_FORM = "games/createGame";
	private static final String VIEW_GAME_LIST = "games/listGames";
	private GameService gameService;
	
	@Autowired
	public GameController(GameService partidaServicio) {
		this.gameService = partidaServicio;
	}
	
	@GetMapping(value = "/create")
	public String iniciarFormulario(Map<String, Object> model) {
		Game game = new Game();
		List<GameMode> gameModes = Arrays.asList(GameMode.values());
		model.put("game", game);
		model.put("gameModes", gameModes);
		return VIEW_CREATION_FORM;
	}

	@PostMapping(value = "/create")
	public String procesarForlulario(@Valid Game game, BindingResult result) throws DataAccessException, Exception {
		if (result.hasErrors()) {
			return VIEW_CREATION_FORM;
		}
		else {
			game.setDate(LocalDate.now());
			this.gameService.saveGame(game);
			return "redirect:/games/" + game.getId();
		}
	}
	
	@GetMapping("/{gameId}")
	public ModelAndView mostrarPartida(@PathVariable("gameId") int gameId) {
		ModelAndView mav = new ModelAndView("games/gameDetails");
		mav.addObject(this.gameService.getGameById(gameId));
		return mav;
	}
	
	@GetMapping(value = "/")
	public String listarPartidas(Game game, BindingResult result, Map<String, Object> model) {
		List<Game> games = gameService.getGames();
		model.put("games", games);
		return VIEW_GAME_LIST;
		
	}
	
}
