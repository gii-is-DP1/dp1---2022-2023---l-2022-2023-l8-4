package org.springframework.samples.petclinic.player;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.statistics.Achievement;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerService {

	private PlayerRepository playerRepository;
	
	@Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthoritiesService authoritiesService;
	
	@Transactional(readOnly=true)
	public Collection<Player> getAllPlayers(){
		return playerRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Player showPlayersById(Integer id) {
		Optional<Player> result= playerRepository.findById(id);
        return result.isPresent()?result.get():null;
		
	}
	
	@Transactional
    public Collection<Game> gamesByPlayers(Integer id) {
    	Player player = playerRepository.findById(id).get();
        return player.getPlayedGames();
    }
	
	@Transactional
    public Collection<Achievement> achievementsByUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
    	String autenticacion = user.getUsername();
    	Player player = playerRepository.findPlayerByUsername(autenticacion);
        return player.getPlayersAchievement();
    }
	
	@Transactional
	public void deletePlayer(Integer id) throws DataAccessException {
		playerRepository.deleteById(id);
	}
	
	
	
	@Transactional
	public void savePlayer(Player player) throws DataAccessException {
		player.setModificationDate(LocalDate.now());
		player.setLastLogin(LocalDate.now());
	
    	playerRepository.save(player);		

		userService.saveUser(player.getUser());

		authoritiesService.saveAuthorities(player.getUser().getUsername(), "Jugador");
	}	
	
	
	
}
