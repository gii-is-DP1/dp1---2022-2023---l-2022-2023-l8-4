package org.springframework.samples.petclinic.statistics;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class StatisticsServiceTest {

	@Autowired
	private StatisticService statisticService;
	
	@Test
	public void shouldCreatePlayerStatistics() {
		Player player=new Player();
		statisticService.createPlayerStadistic(player);
		Integer gamesWon=0;
		Integer gamesLost=0;
		Integer gamesPlayed=0;
		Integer totalPoints=0;
		assertEquals(player.getStatistic().getGamesWon(), gamesWon);
		assertEquals(player.getStatistic().getGamesLost(), gamesLost);
		assertEquals(player.getStatistic().getGamesPlayed(), gamesPlayed);
		assertEquals(player.getStatistic().getTotalPoints(), totalPoints);
	}
}
