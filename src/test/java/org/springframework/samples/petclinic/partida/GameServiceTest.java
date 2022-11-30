package org.springframework.samples.petclinic.partida;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.game.GameState;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTest {
	

	@Autowired
	private GameService gameService;
	
	@Test
	public void shouldGetPlayersFromGame(){
		try {
			
			List<Player> players = new ArrayList<Player>(this.gameService.getPlayersFromGame(1));
			String[] playersUsername = {"pgmarc","carbersor"};
			int numPlayersExpected = 2;
			
			assertEquals(numPlayersExpected, players.size());
			assertEquals(playersUsername[0], players.get(0).getUser().getUsername());
			assertEquals(playersUsername[1], players.get(1).getUser().getUsername());
		} catch (Exception e) {
			fail();
		}
	}
	
//	@Test
//	public void shouldGetGamesFinalised() {
//		try {
//			Map<String, Object> params = new HashMap<String, Object>();
//			int page = params.get("page") != null ? (Integer.valueOf(params.get("page").toString()) -1) : 0;
//			PageRequest pageRequest = PageRequest.of(page, 5);
//			Page<Game> pagina = this.gameService.getGamesFinalized(pageRequest);
//			List<Game> result = pagina.toList();
//
//			int numGamesFinalized = 5;
//			assertThat(result.size()).isEqualTo(numGamesFinalized);
//			assertThat(result.get(0).getGameCode()).isEqualTo(19);
//			assertThat(result.get(1).getGameCode()).isEqualTo(14);
//		}catch (Exception e){
//			fail();
//		}
//	}
	
	@Test
	public void shouldGetGamesByDateDesc() {
		try {
			List<Game> result = new ArrayList<Game>(this.gameService.getGamesByDateDesc());
			LocalDate date = LocalDate.of(2022, 11, 10);
			assertThat(result.size()).isEqualTo(20);
			assertNotNull(result);
			assertThat(result.get(0).getDate()).isEqualTo(date);
		}catch (Exception e){
			fail();
		}
	}
	

	@Test
	public void shouldThrowException() {
		 Exception exception = assertThrows(Exception.class, () -> { 
			 	this.gameService.getPlayersFromGame(-1);
			 });

		    String expectedMessage = "Game not found";
		    String actualMessage = exception.getMessage();
		   

		    assertTrue(expectedMessage.contains(actualMessage));
	}
}
