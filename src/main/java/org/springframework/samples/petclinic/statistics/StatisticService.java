package org.springframework.samples.petclinic.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StatisticService {
    StatisticRepository statisticRepo;

    @Autowired
    public StatisticService( StatisticRepository sr )
    {
        this.statisticRepo = sr;
    }
    @Transactional(readOnly = true)
    public Integer getWonGames( Integer playerId ) { return this.statisticRepo.getWonGamesByPlayer( playerId ); }

    @Transactional(readOnly = true)
    public Integer getLostGames( Integer playerId ) { return this.statisticRepo.getLostGamesByPlayer( playerId ); }

    @Transactional(readOnly = true)
    public Integer getPlayedGames( Integer playerId ) { return this.statisticRepo.getPlayedGamesByPlayer( playerId ); }
    
    public Statistic getStatisticById(Integer playerId) {  return this.statisticRepo.findById(playerId).get(); }
}

