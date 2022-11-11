package org.springframework.samples.petclinic.player;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
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
	public void deletePlayer(Integer id) throws DataAccessException {
		playerRepository.deleteById(id);
	}
	
	@Transactional
	public void savePlayer(Player player) throws DataAccessException {
		player.setRegisterDate(LocalDate.now());
		player.setModificationDate(LocalDate.now());
		player.setLastLogin(LocalDate.now());
	
    	playerRepository.save(player);		

		userService.saveUser(player.getUser());

		authoritiesService.saveAuthorities(player.getUser().getUsername(), "Jugador");
	}		
}
