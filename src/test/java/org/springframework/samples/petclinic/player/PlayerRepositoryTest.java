package org.springframework.samples.petclinic.player;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.samples.petclinic.statistics.archivements.Achievement;


@DataJpaTest
public class PlayerRepositoryTest {
	
@Autowired	
private PlayerRepository playerRepository;
private List<Player> player;
private int numberOfPlayers;
private Integer playerId;

@BeforeEach
void setup() {
	this.player = this.playerRepository.findAll();
	this.numberOfPlayers = this.player.size();
}


@Test
public void shouldGetAchievementByPlayerId(){
	playerId = 1;
	Page<Achievement> actualAchievemntByPlayer = this.playerRepository.getAchievementsByPlayerId(PageRequest.of(0, 
			5), playerId);
		
	assertNotNull(actualAchievemntByPlayer, "The list of achievements cannot be null");
	assertFalse(actualAchievemntByPlayer.isEmpty());
	
}

}
