package org.springframework.samples.petclinic.playergamedata;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameMode;
import org.springframework.samples.petclinic.game.GameRepository;
import org.springframework.samples.petclinic.game.GameState;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerRepository;


@DataJpaTest
public class PlayerGameDataRepositoryTest {

	@Autowired
	private PlayerGameDataRepository playerGameDataRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Autowired
	private PlayerRepository playerRepository;
	
	private List<PlayerGameData> data;
	
	private int numberOfData;
	
	@BeforeEach
	void setup() {
		this.data = this.playerGameDataRepository.findAll().stream().collect(Collectors.toList());
		this.numberOfData = this.data.size();
	}
	
	@Test
	void shouldFindPlayerGameDataCorrectId() {
		int playerGameDataId = 1;
		PlayerGameData firstData = data.get(0);
		PlayerGameData actualData = this.playerGameDataRepository.findById(playerGameDataId).get();
		
		assertEquals(firstData.getId(), actualData.getId(), "The data id does not match");
		assertEquals(firstData.getPointsNumber(), actualData.getPointsNumber(), "The points number does not match");
		assertEquals(firstData.getGame(), actualData.getGame(), "The game of the data does not match");
		assertEquals(firstData.getPlayer(), actualData.getPlayer(), "The player of the data does not match");
		assertEquals(firstData.getActualCard(), actualData.getActualCard(), "The actual card does not match");
	}
	
	@Test
	void shouldFindPlayerGameDataCorrectGameAndPlayerIds() {
		int playerId = 1;
		int gameId= 1;
		PlayerGameData firstData = data.stream().collect(Collectors.toList()).get(0);
		PlayerGameData actualData = this.playerGameDataRepository.findByIds(gameId,playerId);
		
		assertEquals(firstData.getId(), actualData.getId(), "The data id does not match");
		assertEquals(firstData.getPointsNumber(), actualData.getPointsNumber(), "The points number does not match");
		assertEquals(firstData.getGame(), actualData.getGame(), "The game of the data does not match");
		assertEquals(firstData.getPlayer(), actualData.getPlayer(), "The player of the data does not match");
		assertEquals(firstData.getActualCard(), actualData.getActualCard(), "The actual card does not match");
	}
	
	private PlayerGameData createData() {
		PlayerGameData data = new PlayerGameData();
		int playerGameDataId = this.numberOfData + 1;
		int playerId= 1;
		int gameId= 2;
				
		data.setId(playerGameDataId);
		data.setGame(this.gameRepository.findById(gameId).get());
		data.setPlayer(this.playerRepository.findById(playerId).get());
		data.setPointsNumber(0);
		return data;
	}

	@Test
	void shouldSaveData() {
		PlayerGameData expectedData = createData();
		this.playerGameDataRepository.save(expectedData);
		int playerGameDataId= 14;
		PlayerGameData actualData = this.playerGameDataRepository.findById(playerGameDataId).get();
		assertEquals(expectedData.getId(), actualData.getId(), "The data id does not match");
		assertEquals(expectedData.getPointsNumber(), actualData.getPointsNumber(),"The points number does not match");	
		assertEquals(expectedData.getGame(), actualData.getGame(), "The game of the data does not match");	
		assertEquals(expectedData.getPlayer(), actualData.getPlayer(), "The player of the data does not match");
	}
	
	@Test
	void shouldGetDataByGameId() {
		int gameId= 1;
		List<PlayerGameData> expectedGameDataList= new ArrayList<>();
		expectedGameDataList.add(this.data.get(0));
		expectedGameDataList.add(this.data.get(1));
		List<PlayerGameData> actualGameDataList=this.playerGameDataRepository.getDataByGameId(gameId);
		int i=0;
		while(i<expectedGameDataList.size()) {
			PlayerGameData expectedGameData= expectedGameDataList.get(i);
			PlayerGameData actualGameData= actualGameDataList.get(i);
			assertEquals(expectedGameData.getId(), actualGameData.getId(), "The data id does not match");
			assertEquals(expectedGameData.getPointsNumber(), actualGameData.getPointsNumber(), "The points number does not match");
			assertEquals(expectedGameData.getGame(), actualGameData.getGame(), "The game of the data does not match");
			assertEquals(expectedGameData.getPlayer(), actualGameData.getPlayer(), "The player of the data does not match");
			assertEquals(expectedGameData.getActualCard(), actualGameData.getActualCard(), "The actual card does not match");
			i++;
		}
		
	}
}
