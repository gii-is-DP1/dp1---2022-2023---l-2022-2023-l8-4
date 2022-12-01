package org.springframework.samples.petclinic.player;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.game.Game;
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
	
	@Transactional(readOnly=true)
	public Player getPlayerByUsername(String username) throws Exception{
		Player player = playerRepository.findPlayerByUsername(username);
		if(player == null) {
			throw new Exception("Player not found");
		}
		return player;
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
	public void savePlayer(Player newPlayer) throws DataAccessException {
		if(newPlayer.isNew()) {
			newPlayer.setModificationDate(LocalDate.now());
			newPlayer.setRegisterDate(LocalDate.now());
			newPlayer.setModificationDate(LocalDate.now());
			playerRepository.save(newPlayer);
			return;
		}
		Player playerModified = this.showPlayerById(newPlayer.getId());
		
		playerModified.setModificationDate(LocalDate.now());
    	playerModified.setEmail(newPlayer.getEmail());
    	playerModified.setUser(newPlayer.getUser());
    	playerModified.setProfilePicture(newPlayer.getProfilePicture());
		
    	playerRepository.save(playerModified);		

		userService.saveUser(playerModified.getUser());

		authoritiesService.saveAuthorities(playerModified.getUser().getUsername(), "Jugador");
	}		
}
