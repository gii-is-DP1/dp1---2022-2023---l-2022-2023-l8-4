package org.springframework.samples.petclinic.game;

import java.time.LocalDate;
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
	
	private static final String GAME_CREATION_FORM = "games/createGame";
	private static final String GAME_DETAILS = "games/detallesPartida";
	private static final String GAME_LISTING = "games/listaPartidas";
	private final GameService gameService;
	
	@Autowired
	public GameController(GameService gameService) {
		this.gameService = gameService;
	}
	
	@GetMapping(value = "/create")
	public String iniciarFormulario(Map<String, Object> model) {
		Game game = new Game();
		game.setDate(LocalDate.now());
		model.put("game", game);
		return GAME_CREATION_FORM;
	}

	@PostMapping(value = "/create")
	public String procesarForlulario(@Valid Game game, BindingResult result) throws DataAccessException, Exception {
		if (result.hasErrors()) {
			return GAME_CREATION_FORM;
		}
		
		this.gameService.saveGame(game);
		return "redirect:/games/" + game.getId();

	}
	
	@GetMapping("/{gameId}")
	public ModelAndView mostrarPartida(@PathVariable("gameId") int partidaId) {
		ModelAndView mav = new ModelAndView(GAME_DETAILS);
		mav.addObject("game", this.gameService.getGameById(partidaId));
		return mav;
	}
	
	@GetMapping
	public String listarPartidas(Game partida, BindingResult result, Map<String, Object> model) {
		List<Game> partidas = gameService.getGames();
		model.put("games", partidas);
		return GAME_LISTING;
		
	}
	
}
