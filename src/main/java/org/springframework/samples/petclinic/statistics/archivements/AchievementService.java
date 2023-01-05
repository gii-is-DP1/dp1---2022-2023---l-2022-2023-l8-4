package org.springframework.samples.petclinic.statistics.archivements;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    	for(Achievement achievement : page) {
    		calculatePercentage(achievement);
    	}
    	return page;
    }

    @Transactional
    public void save(Achievement achievement){
        repo.save(achievement);
    }
    
    private void calculatePercentage(Achievement achievement) {
    	int numOfPlayersWithTheAchievement = achievement.getPlayers().size(); 
    	int numOfPlayers = playerService.getNumberOfPlayers();
    	double percentage = 100.*numOfPlayersWithTheAchievement/numOfPlayers;
    	achievement.setPercentage(percentage);
    }
    
    public List<Achievement> checkNewAchievements(Player player, PlayerGameData data){
    	List<Achievement> res = new ArrayList<Achievement>();
    	Integer gamesWon = player.getStatistic().getGamesWon(); //IMPORTANTE: se debe actualizar el jugador antes de llamar a este método
    	Integer gamesPlayed = player.getStatistic().getGamesPlayed();
    	Integer pointsInGame = data.getPointsNumber();
    	
    	if(checkAchievement(res, gamesPlayed, 10, 1)) {
    		res.add(this.getAchievementById(1));
    	}
    	if(checkAchievement(res, gamesPlayed, 20, 2)) {
    		res.add(this.getAchievementById(2));
    	}
    	if(checkAchievement(res, gamesPlayed, 100, 3)) {
    		res.add(this.getAchievementById(3));
    	}
    	if(checkAchievement(res, gamesWon, 10, 4)) {
    		res.add(this.getAchievementById(4));
    	}
    	if(checkAchievement(res, pointsInGame, 50, 5)) {
    		res.add(this.getAchievementById(5));
    	}
    	return res;
    }
    
    private boolean checkAchievement(List<Achievement> list, Integer achieved, Integer treshold, Integer achievementId) {
    	if(list.contains(this.getAchievementById(achievementId))) { //puede que haga falta cambiar la lista list por una lista con las IDs
    		return false;
    	}
    	else {
    		if(achieved >= treshold) {
    			return true;
    		}
    	}
    	return false;
    }
    

}
