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
    
    public List<Achievement> checkAchievements(Player player, PlayerGameData data){
    	List<Achievement> res = player.getPlayersAchievement().stream().collect(Collectors.toList());
    	Integer gamesWon = player.getStatistic().getGamesWon(); //IMPORTANTE: se debe actualizar el jugador antes de llamar a este método
    	Integer gamesPlayed = player.getStatistic().getGamesPlayed();
    	Integer pointsInGame = data.getPointsNumber();
    	
    	if(getsGamer(res, gamesPlayed)) {
    		res.add(this.getAchievementById(1));
    	}
    	if(getsSoldier(res, gamesPlayed)) {
    		res.add(this.getAchievementById(2));
    	}
    	if(getsKing(res, gamesPlayed)) {
    		res.add(this.getAchievementById(3));
    	}
    	if(getsSensei(res, gamesWon)) {
    		res.add(this.getAchievementById(4));
    	}
    	if(getsDobble(res, pointsInGame)) {
    		res.add(this.getAchievementById(5));
    	}
    	return res;
    }
    
    private boolean getsGamer(List<Achievement> list, Integer gamesPlayed) {
    	if(list.contains(this.getAchievementById(1))) { //puede que haga falta cambiar la lista list por una lista con las IDs
    		return false;
    	}
    	else {
    		if(gamesPlayed >= 10) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private boolean getsSoldier(List<Achievement> list, Integer gamesPlayed) {
    	if(list.contains(this.getAchievementById(2))) {
    		return false;
    	}
    	else {
    		if(gamesPlayed >= 20) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private boolean getsKing(List<Achievement> list, Integer gamesPlayed) {
    	if(list.contains(this.getAchievementById(3))) {
    		return false;
    	}
    	else {
    		if(gamesPlayed >= 100) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private boolean getsSensei(List<Achievement> list, Integer gamesWon) {
    	if(list.contains(this.getAchievementById(4))) {
    		return false;
    	}
    	else {
    		if(gamesWon >= 10) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private boolean getsDobble(List<Achievement> list, Integer points) {
    	if(list.contains(this.getAchievementById(5))) {
    		return false;
    	}
    	else {
    		if(points == (53-2)) {
    			return true;
    		}
    	}
    	return false;
    }

}
