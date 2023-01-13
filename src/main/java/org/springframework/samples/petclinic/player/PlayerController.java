package org.springframework.samples.petclinic.player;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.statistics.archivements.Achievement;
import org.springframework.samples.petclinic.statistics.archivements.AchievementService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
	
	Logger logger = LoggerFactory.getLogger(PlayerController.class);

	private PlayerService playerService;
	private AchievementService achievementService;
	public static final String player_listing = "players/playerList";
    public static final String player_editing = "players/updatePlayer";
    public static final String achievement_listing = "achievements/AchievementsListing";
    public static final String player_profile = "players/dataPlayer";
    public static final String welcome = "welcome";

	@Autowired
	public PlayerController(PlayerService playerService, AchievementService achievementService) {
		this.playerService = playerService;
		this.achievementService = achievementService;
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
    public String showAllAchievementPlayers( @PathVariable("id") Integer id, ModelMap model, @RequestParam Map<String, Object> params) throws NoSuchEntityException {
		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) : 0;
		PageRequest pageRequest = PageRequest.of(page, 5);
		Page<Achievement> pageAchievements= playerService.showAchievementsByPlayerId(id, pageRequest);
		achievementService.calculatePercentageOfEachAchievement(pageAchievements);
		int totalPages = pageAchievements.getTotalPages();
		List<Integer> pages=new ArrayList<>();
		if(totalPages > 0) {
			pages= IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
		}
		
        model.put("achievements", pageAchievements.getContent());
        model.put("pages", pages);
        model.put("current", page + 1);
        model.put("next", page + 2);
        model.put("prev", page);
        model.put("last", totalPages);
        return achievement_listing;
    }


    @GetMapping("/data/{username}")
    public ModelAndView getDataFromPlayer( @PathVariable("username") String username,
    		@RequestParam Map<String, Object> params) throws NoSuchEntityException {
    		ModelAndView result=new ModelAndView(player_profile);
    		int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) : 0;

    		PageRequest pageRequest = PageRequest.of(page, 3);
    		Player player=playerService.getPlayerByUsername(username);
    		Page<Game> pageGamesByPlayerId= playerService.gamesByPlayerId(player.getId(), pageRequest);

    		int totalPages = pageGamesByPlayerId.getTotalPages();
    		List<Integer> pages=new ArrayList<>();
    		if(totalPages > 0) {
    			pages= IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
    		}
    		
    		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		User user = (User)authentication.getPrincipal();
        	String autenticacion = user.getUsername();
        	
        	result.addObject("username", autenticacion);
    		result.addObject( "player", this.playerService.getPlayerById( player.getId() ) );
    		result.addObject( "games", pageGamesByPlayerId.getContent());
    		result.addObject( "pages", pages);
    		result.addObject( "current", page + 1);
    		result.addObject( "next", page + 2);
    		result.addObject( "prev", page);
    		result.addObject( "last", totalPages);
            return result;
    }

	@GetMapping("/delete/{id}")
    public ModelAndView deletePlayersById(@PathVariable("id") Integer id) throws NoSuchEntityException{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
    	String autenticacion = user.getUsername();
    	Player player=this.playerService.getPlayerByUsername(autenticacion);
		
		playerService.deletePlayer(id);
		logger.info("Dobble::INFO::Player Player with id " + id + " has been removed from the system");
	
        if(player.getId() != id) {
        	 return showAllPlayers(new HashMap<>());
        }
        
        return new ModelAndView(welcome);
    }

	@GetMapping("/edit/{id}")
    public ModelAndView editPlayer(@PathVariable("id") Integer id) throws NoSuchEntityException {
        ModelAndView result = new ModelAndView(player_editing);
        result.addObject("player", playerService.getPlayerById(id));
        return result;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView editPlayer(@PathVariable("id") Integer id, @Valid Player newPlayer,BindingResult br,
    		@RequestParam Map<String, Object> params) throws NoSuchEntityException {
    	ModelAndView result=null;
    	if(br.hasErrors()) {
    		result = new ModelAndView(player_editing);
    		result.addObject("player", newPlayer);
    		System.out.println(br.getAllErrors());
    		return result;
    	}
        Player playerModified = playerService.getPlayerById(id);
        if(playerModified !=null) {
        	playerService.savePlayer(newPlayer);
            result =  getDataFromPlayer(newPlayer.getUser().getUsername(), params);
            result.addObject("message", "Player edit succesfully");
            return result;
         }
         result = getDataFromPlayer(newPlayer.getUser().getUsername(), params);
         result.addObject("message", "Player with id "+id+" don't edit succesfully");
         return result;
        }



}
