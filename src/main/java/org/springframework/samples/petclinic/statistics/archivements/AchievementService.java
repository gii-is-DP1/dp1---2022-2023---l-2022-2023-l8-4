package org.springframework.samples.petclinic.statistics.archivements;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
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


}
