package org.springframework.samples.petclinic.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.playergamedata.PlayerGameData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatisticService {
    StatisticRepository statisticRepository;

    @Autowired
    public StatisticService( StatisticRepository statisticRepository ){
        this.statisticRepository = statisticRepository;
    }
    
    public void saveStatistic(Statistic statistic) {
    	this.statisticRepository.save(statistic);
    }
    
    @Transactional
    public void updatePlayerStadistics(Player player, PlayerGameData data, Statistic statistics, Player winner) {
		if(player.equals(winner)) {
			statistics.setGamesWon(statistics.getGamesWon()+1);
		} else {
			statistics.setGamesLost(statistics.getGamesLost()+1);
		}
		statistics.setGamesPlayed(statistics.getGamesPlayed()+1);
		statistics.setTotalPoints(statistics.getTotalPoints() + data.getPointsNumber());
		player.setStatistic(statistics);
	}
    
    @Transactional
	public void createPlayerStadistic(Player newPlayer) {
		Statistic statistic=new Statistic();
		statistic.setGamesPlayed(0);
		statistic.setGamesLost(0);
		statistic.setGamesWon(0);
		statistic.setTotalPoints(0);
		saveStatistic(statistic);
		newPlayer.setStatistic(statistic);
	}
}

