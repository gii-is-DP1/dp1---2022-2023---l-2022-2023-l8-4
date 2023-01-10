package org.springframework.samples.petclinic.statistics.archivements;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.playergamedata.PlayerGameData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AchievementService {

    AchievementRepository repo;
    PlayerService playerService;

    @Autowired
    public AchievementService(AchievementRepository repo, PlayerService playerService){
        this.repo=repo;
        this.playerService = playerService;
    }
    
    @Transactional(readOnly = true)
	public Achievement getAchievementsByName(String name ) throws  NoSuchEntityException, DataAccessException {
    	Achievement achievement = repo.findAchievementByName(name);
    	if(achievement == null) {
			throw new NoSuchEntityException("404", "Achievement not found");
		}
		return achievement;
	}

    
    @Transactional(readOnly = true) 
    public Achievement getAchievementById(Integer id) throws DataAccessException {
    	Optional<Achievement> achievement = repo.findById(id);
    	return achievement.isPresent()?achievement.get():null;
    }
    
    @Transactional(readOnly = true)
	public Page<Achievement> getAchievements(Pageable pageable) throws DataAccessException{
    	Page<Achievement> page = repo.findAll(pageable);
    	calculatePercentageOfEachAchievement(page);
    	return page;
    }
    
    @Transactional
    public void calculatePercentageOfEachAchievement(Page<Achievement> page) {
    	for(Achievement achievement : page) {
    		int numberOfPlayersWithTheAchievement = achievement.getPlayers().size(); 
    		int numberOfPlayers = playerService.getNumberOfPlayers();
    		double percentage = 100.*numberOfPlayersWithTheAchievement/numberOfPlayers;
    		achievement.setPercentage(percentage);
    	}
    	
    }

    @Transactional
    public void save(Achievement achievement) throws DataAccessException{
    	achievement.setPercentage(0.0);
        repo.save(achievement);
    }
    
    @Transactional
    public void deleteAchievements(Integer id) throws DataAccessException{
    	repo.deleteById(id);
    	
    }
    
    @Transactional(readOnly = true) 
    public List<Achievement> checkPlayerNewAchievements(Player player, PlayerGameData playerGameData){
    	List<Achievement> playerAchievements = new ArrayList<Achievement>();
    	Integer gamesWon = player.getStatistic().getGamesWon(); 
    	Integer gamesPlayed = player.getStatistic().getGamesPlayed();
    	Integer pointsInGame = playerGameData.getPointsNumber();
    	
    	if(achievementIsCompleted(playerAchievements, gamesPlayed, 10, 1)) {
    		playerAchievements.add(this.getAchievementById(1));
    	}
    	if(achievementIsCompleted(playerAchievements, gamesPlayed, 20, 2)) {
    		playerAchievements.add(this.getAchievementById(2));
    	}
    	if(achievementIsCompleted(playerAchievements, gamesPlayed, 100, 3)) {
    		playerAchievements.add(this.getAchievementById(3));
    	}
    	if(achievementIsCompleted(playerAchievements, gamesWon, 10, 4)) {
    		playerAchievements.add(this.getAchievementById(4));
    	}
    	if(achievementIsCompleted(playerAchievements, pointsInGame, 50, 5)) {
    		playerAchievements.add(this.getAchievementById(5));
    	}
    	return playerAchievements;
    }
    
    @Transactional(readOnly = true) 
    private boolean achievementIsCompleted(List<Achievement> playerAchievements, Integer achieved, Integer target, Integer achievementId) {
    	if(playerAchievements.contains(this.getAchievementById(achievementId))) { 
    		return false;
    	}
    	if(achieved >= target) {
    		return true;
    	}
    	return false;
    }
    


}
