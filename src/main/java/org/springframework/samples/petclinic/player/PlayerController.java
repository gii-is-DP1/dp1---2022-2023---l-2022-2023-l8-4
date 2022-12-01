package org.springframework.samples.petclinic.player;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Collection;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.statistics.Achievement;
import org.springframework.samples.petclinic.statistics.AchievementService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/players")
public class PlayerController {

	private PlayerService playerService;
	public static final String player_listing = "players/playerList";
    public static final String player_editing = "players/createOrUpdatePlayer";
    public static final String achievement_listing = "achievements/AchievementsListing";
    public static final String player_profile = "players/dataPlayer";

	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
		
	}

	@GetMapping
    public ModelAndView showAllPlayers(@RequestParam Map<String, Object> params) {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Player> pagePlayer= playerService.getAllPlayers(pageRequest);
		
		int totalPages = pagePlayer.getTotalPages();
		List<Integer> pages=new ArrayList<>();
		if(totalPages > 0) {
			pages= IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		}
		
        ModelAndView result = new ModelAndView(player_listing);
        result.addObject("players", pagePlayer.getContent());
        result.addObject("pages", pages);
        result.addObject("current", page + 1);
        result.addObject("next", page + 2);
        result.addObject("prev", page);
        result.addObject("last", totalPages);
        return result;
	}

	@GetMapping("/{id}/achievements")
    public ModelAndView showAllAchievementGames(@PathVariable("id") Integer id) {
        ModelAndView result = new ModelAndView(achievement_listing);
        result.addObject("achievements", playerService.achievementsByUsername());
        return result;
    }


    @GetMapping("/data/{id}")
    public String getDataFromPlayer( @PathVariable("id") Integer id, ModelMap model, @RequestParam Map<String, Object> params) {
    	int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) : 0;
		
		PageRequest pageRequest = PageRequest.of(page, 3);
		Page<Game> pageGamesByPlayerId= playerService.gamesByPlayerId(id, pageRequest);
		
		int totalPages = pageGamesByPlayerId.getTotalPages();
		List<Integer> pages=new ArrayList<>();
		if(totalPages > 0) {
			pages= IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		}
    	
        model.put( "player", this.playerService.showPlayerById( id ) );
        model.put( "games", pageGamesByPlayerId.getContent());
        model.put( "pages", pages);
        model.put( "current", page + 1);
        model.put( "next", page + 2);
        model.put( "prev", page);
        model.put( "last", totalPages);
        return player_profile;
    }

	@GetMapping("/delete/{id}")
    public ModelAndView deletePlayersById(@PathVariable("id") Integer id) {
		playerService.deletePlayer(id);
        return showAllPlayers(new HashMap<>());
    }

	@GetMapping("/edit/{id}")
    public ModelAndView editJugador(@PathVariable("id") Integer id) {
        ModelAndView result = new ModelAndView(player_editing);
        result.addObject("player", playerService.showPlayerById(id));
        return result;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editJugador(@PathVariable("id") Integer id, @Valid Player newPlayer,BindingResult br) {
        ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(player_editing);
            result.addAllObjects(br.getModel());
            return result;
        }else {
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
            result = showAllPlayers(new HashMap<>());
            result.addObject("message", "Jugador creado satisfactoriamente");

        }
        return result;
    }

}
