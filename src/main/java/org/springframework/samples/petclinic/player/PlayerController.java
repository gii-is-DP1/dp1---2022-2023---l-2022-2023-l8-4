package org.springframework.samples.petclinic.player;


import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public static final String player_listing = "players/playerList";
    public static final String player_editing = "players/editPlayer";
	
	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}
	
	@GetMapping("")
    public ModelAndView showAllPlayers() {
        ModelAndView result = new ModelAndView(player_listing);
        result.addObject("players", playerService.getAllPlayers());
        return result;
	}
	
	@GetMapping("/delete/{id}")
    public ModelAndView deletePlayersById(@PathVariable("id") Integer id) {
		playerService.deletePlayer(id);
        return showAllPlayers();
    }
	
	@GetMapping("/edit/{id}")
    public ModelAndView editJugador(@PathVariable("id") Integer id) {
        ModelAndView result = new ModelAndView(player_editing);
        result.addObject("jugadores", playerService.showPlayersById(id));
        return result;
    }
	
    @PostMapping("/edit/{id}")
    public ModelAndView editJugador(@PathVariable("id") Integer id, @Valid Player jugador2,BindingResult br) {        
        ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(player_editing);
            result.addAllObjects(br.getModel());
        }else {
            Player jugador = playerService.showPlayersById(id);
            if(jugador !=null) {
                BeanUtils.copyProperties(jugador, jugador2,"id");
                
                playerService.savePlayer(jugador2);
                result = showAllPlayers();
                result.addObject("message", "Jugador editado satisfactoriamente");
                result = new ModelAndView("Bienvenido");
               
            }else {
                result = showAllPlayers();
                result.addObject("message", "Jugador con id "+id+" no ha sido editado correctamente");
            }
           
            
        }
        return result;
        
    }
    
    @GetMapping("/create")
    public ModelAndView createJugador() {
        ModelAndView result = new ModelAndView(player_editing);
        Player jugador = new Player();
        result.addObject("jugador", jugador);
        return result;
        
    }
    
    
    @PostMapping("/create")
    public ModelAndView saveNewJugador(@Valid Player player,BindingResult br) {
        ModelAndView result=null;
        if(br.hasErrors()) {
            result = new ModelAndView(player_editing);
            result.addAllObjects(br.getModel());
        }else {
            playerService.savePlayer(player);
            result = showAllPlayers();
            result.addObject("message", "Jugador creado satisfactoriamente");
            result = new ModelAndView("Bienvenido");
            
        }
        return result;
    }

}