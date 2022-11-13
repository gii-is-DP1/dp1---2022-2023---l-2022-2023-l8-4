package org.springframework.samples.petclinic.player;


import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.GameService;
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
    private GameService gameService;
	public static final String PLAYER_LISTING = "players/playerList";
    public static final String PLAYER_DATA = "players/dataPlayer";
    public static final String PLAYER_CREATING_OR_EDITING = "players/createOrUpdatePlayer";

	@Autowired
	public PlayerController(PlayerService playerService, GameService gameService ) {
		this.playerService = playerService;
        this.gameService = gameService;
	}

    @GetMapping("")
    public ModelAndView showAllPlayers() {
        ModelAndView result = new ModelAndView(PLAYER_LISTING);
        result.addObject("players", playerService.getAllPlayers());
        return result;
	}

	@GetMapping("/delete/{id}")
    public ModelAndView deletePlayersById(@PathVariable("id") Integer id) {
        playerService.deletePlayer(id);
        return showAllPlayers();
    }


	@GetMapping("/edit/{id}")
    public String editPlayer(@PathVariable("id") Integer id, ModelMap model) {
        model.put("player", playerService.showPlayersById( id ) );
        return PLAYER_CREATING_OR_EDITING;
    }

    @PostMapping("/edit/{id}")
    public String editPlayer(@PathVariable("id") Integer id, @Valid Player player, BindingResult br, ModelMap model) {
        if( br.hasErrors() ) {
            model.put( "player", player );
            return PLAYER_CREATING_OR_EDITING;
        }else {
            Player uPlayer = playerService.showPlayersById( id );
            if ( uPlayer == null )
            {
                return PLAYER_CREATING_OR_EDITING;
            }
            BeanUtils.copyProperties(uPlayer, player);
            playerService.savePlayer( uPlayer );
            return PLAYER_LISTING;

        }

    }

    @GetMapping("/create")
    public String createJugador( ModelMap model )
    {
        model.put( "player", new Player() );
        return PLAYER_CREATING_OR_EDITING;
    }


    @PostMapping("/create")
    public String saveNewJugador(@Valid Player player,BindingResult br, ModelMap model)
    {
        if( br.hasErrors() ) {
            return PLAYER_CREATING_OR_EDITING;
        }
            playerService.savePlayer(player);
        return PLAYER_LISTING;
    }

    @GetMapping("/data/{id}")
    public String getDataFromPlayer( @PathVariable("id") Integer id, ModelMap model ) {;
        model.put( "player", this.playerService.showPlayersById( id ) );
        model.put( "games", this.gameService.gamesByPlayers( id ) );
        return PLAYER_DATA;
    }

}
