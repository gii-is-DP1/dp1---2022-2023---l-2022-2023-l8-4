package org.springframework.samples.petclinic.player;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.statistics.StatisticService;
import org.springframework.samples.petclinic.statistics.archivements.Achievement;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PlayerService {

	private PlayerRepository playerRepository;
	private StatisticService statisticService;
	private UserService userService;
	private AuthoritiesService authoritiesService;

	@Autowired
	public PlayerService(PlayerRepository playerRepository, StatisticService statisticService,
			UserService userService, AuthoritiesService authoritiesService) {
		this.playerRepository = playerRepository;
		this.statisticService = statisticService;
		this.userService = userService;
		this.authoritiesService = authoritiesService;
	}

	@Transactional(readOnly=true)
	public Page<Player> getAllPlayers(Pageable pageable){
		return playerRepository.findAll(pageable);
	}

	@Transactional(readOnly=true)
	public Player getPlayerByUsername(String username) throws NoSuchEntityException, DataAccessException{
		Player player = playerRepository.findPlayerByUsername(username);
		if(player == null) {
			throw new NoSuchEntityException("404", "Player not found");
		}
		return player;
	}

	@Transactional(readOnly = true)
	public Player getPlayerById(Integer id) throws NoSuchEntityException {
		Player player= playerRepository.findById(id).orElseThrow(()-> new NoSuchEntityException("404","Not found"));
        return player;

	}
	@Transactional(readOnly=true)
    public Page<Achievement> showAchievementsByPlayerId(Integer id, Pageable pageable) throws NoSuchEntityException {
		Player player = playerRepository.findById(id).orElseThrow(()->new NoSuchEntityException("404", "Not found"));
		
        return playerRepository.getAchievementsByPlayerId(pageable, player.getId());
    }

	@Transactional(readOnly = true)
    public Page<Game> gamesByPlayerId(Integer id, Pageable pageable) {
    	return playerRepository.getGamesByPlayerId(pageable, id);
    }

	@Transactional
 	public void deletePlayer(Integer id) throws DataAccessException {
		playerRepository.deleteById(id);
	}
	
	@Transactional
	public void addAchievementToPlayer(Player player, Achievement achievement) throws DataAccessException, NoSuchEntityException {
		Collection<Achievement> playerAchievements=player.getPlayersAchievement();
		playerAchievements.add(achievement);
		player.setPlayersAchievement(playerAchievements);
		savePlayer(player);
	}

	@Transactional
	public void savePlayer(Player Player) throws DataAccessException, NoSuchEntityException {
		if(Player.isNew()) {
			saveNewPlayer(Player);
		}
		updatePlayer(Player);
	}
	
	@Transactional
	private void saveNewPlayer(Player newPlayer) {
		newPlayer.setModificationDate(LocalDate.now());
		newPlayer.setRegisterDate(LocalDate.now());
		newPlayer.setLastLogin(LocalDate.now());
		statisticService.createPlayerStadistic(newPlayer);
		
		playerRepository.save(newPlayer);
		userService.saveUser(newPlayer.getUser());
		authoritiesService.saveAuthorities(newPlayer.getUser().getUsername(), "Player");
	}

	@Transactional
	private void updatePlayer(Player Player) throws NoSuchEntityException {
		Player playerModified = this.getPlayerById(Player.getId());

		playerModified.setModificationDate(LocalDate.now());
    	playerModified.setEmail(Player.getEmail());
    	playerModified.setUser(Player.getUser());
    	playerModified.setProfilePicture(Player.getProfilePicture());

    	playerRepository.save(playerModified);
		userService.saveUser(playerModified.getUser());
		authoritiesService.saveAuthorities(playerModified.getUser().getUsername(), "Player");
	}
	
	@Transactional
	public int getNumberOfPlayers() {
		return playerRepository.getNumberOfPlayers();
	}

}
