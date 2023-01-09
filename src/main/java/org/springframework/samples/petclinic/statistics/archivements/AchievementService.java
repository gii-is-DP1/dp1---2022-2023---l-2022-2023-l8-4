package org.springframework.samples.petclinic.statistics.archivements;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Achievement getAchievementById(Integer id){
    	Optional<Achievement> achievement = repo.findById(id);
    	return achievement.isPresent()?achievement.get():null;
    }
    
    @Transactional(readOnly = true)
    Page<Achievement> getAchievements(Pageable pageable){
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
    public void save(Achievement achievement){
        repo.save(achievement);
    }
    
    
    public List<Achievement> checkPlayerNewAchievements(Player player, PlayerGameData playerGameData){
    	List<Achievement> playerAchievements = new ArrayList<Achievement>(player.getPlayersAchievement());
    	Integer gamesWon = player.getStatistic().getGamesWon(); 
    	Integer gamesPlayed = player.getStatistic().getGamesPlayed();
    	Integer pointsInGame = playerGameData.getPoints();
    	
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
