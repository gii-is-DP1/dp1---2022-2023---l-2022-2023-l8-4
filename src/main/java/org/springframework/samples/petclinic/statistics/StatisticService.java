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
}

