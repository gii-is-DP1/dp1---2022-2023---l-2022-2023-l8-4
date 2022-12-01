package org.springframework.samples.petclinic.player;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.statistics.AchievementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/players")
public class PlayerController {

	private PlayerService playerService;
	private AchievementService achievementService;
	public static final String player_listing = "players/playerList";
    public static final String player_editing = "players/createOrUpdatePlayer";
    public static final String game_listing = "games/listGames";
    public static final String achievement_listing = "achievements/AchievementsListing";
    public static final String player_profile = "players/dataPlayer";

	@Autowired
	public PlayerController(PlayerService playerService, AchievementService achievementService) {
		this.playerService = playerService;
		this.achievementService = achievementService;
	}

	@GetMapping
    public ModelAndView showAllPlayers() {
        ModelAndView result = new ModelAndView(player_listing);
        result.addObject("players", playerService.getAllPlayers());
        return result;
	}

	@GetMapping("/{id}/games")
    public ModelAndView showAllPlayerGames(@PathVariable("id") Integer id) {
        ModelAndView result = new ModelAndView(game_listing);
        result.addObject("games", playerService.gamesByPlayers(id));
        return result;
	}


	@GetMapping("/{id}/achievements")
    public ModelAndView showAllAchievementGames(@PathVariable("id") Integer id) {
        ModelAndView result = new ModelAndView(achievement_listing);
        result.addObject("achievements", achievementService.findAchievementByPlayerId(id));
        return result;
    }


    @GetMapping("/data/{id}")
    public String getDataFromPlayer( @PathVariable("id") Integer id, ModelMap model ) {;
        model.put( "player", this.playerService.showPlayersById( id ) );
        model.put( "games", this.playerService.gamesByPlayers(id) );
        return player_profile;
    }

	@GetMapping("/delete/{id}")
    public ModelAndView deletePlayersById(@PathVariable("id") Integer id) {
		playerService.deletePlayer(id);
        return showAllPlayers();
    }

	@GetMapping("/edit/{id}")
    public ModelAndView editJugador(@PathVariable("id") Integer id) {
        ModelAndView result = new ModelAndView(player_editing);
        result.addObject("player", playerService.showPlayersById(id));
        return result;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editJugador(@PathVariable("id") Integer id, @Valid Player jugador2,BindingResult br) {
        ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(player_editing);
            result.addAllObjects(br.getModel());
            return result;
        }
        Player playerModified = playerService.showPlayerById(id);
        if(playerModified !=null) {
        	playerService.savePlayer(newPlayer);
            result = showAllPlayers(null);
            result.addObject("message", "Jugador editado satisfactoriamente");
            return result;
         }
         result = showAllPlayers(null);
         result.addObject("message", "Jugador con id "+id+" no ha sido editado correctamente");
         return result;
    }

    @GetMapping("/new")
    public ModelAndView createJugador() {
        ModelAndView result = new ModelAndView(player_editing);
        Player jugador = new Player();
        result.addObject("player", jugador);
        return result;

    }


    @PostMapping("/new")
    public ModelAndView saveNewJugador(@Valid Player player, BindingResult br) {
        ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(player_editing);
            result.addAllObjects(br.getModel());
        }else {
            playerService.savePlayer(player);
            result = showAllPlayers();
            result.addObject("message", "Jugador creado satisfactoriamente");

        }
        return result;
    }

}
