package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameMode;
import org.springframework.samples.petclinic.game.GameRepository;
import org.springframework.samples.petclinic.player.Player;


@DataJpaTest
public class GameRepositoryTest {
	
	@Autowired
	private GameRepository gameRepository;
	
	private Collection<Player> players;
	
	
	@BeforeEach
	void setup() {
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		
		this.players = List.of(player1, player2, player3);
	}
	
	@Test
	void shouldFindGameCorrectId() {
		int gameId = 1;
		Game game = new Game();
		game.setId(gameId);
		game.setGameMode(GameMode.ESTANDAR);;
		game.setDate(LocalDate.of(2022, 11, 4));
		Optional<Game> expectedGame = Optional.of(game);
		
		Optional<Game> actualGame = this.gameRepository.findById(gameId);
		assertEquals(expectedGame.get().getId(), actualGame.get().getId(), "El id de la partida no coincide");
		assertEquals(expectedGame.get().getDate(), actualGame.get().getDate(), "La fecha de la partida no coincide");
		assertEquals(expectedGame.get().getGameMode(), actualGame.get().getGameMode(), "El modo partida no coincide");

	}
	
	@Test
	void shouldNotFindGameInvalidId() {
		int gameId = Integer.MIN_VALUE;
		Optional<Game> expectedGame = Optional.empty();
		
		Optional<Game> actualGame = this.gameRepository.findById(gameId);
		
		assertEquals(expectedGame, actualGame, "It should not find the game");
	}

	@Test
	void shouldSaveGame() {
		
		Game expectedGame = new Game();
		int gameId = 6;
		expectedGame.setId(gameId);
		expectedGame.setDate(LocalDate.now());
		expectedGame.setGameMode(GameMode.EL_FOSO);
		expectedGame.setPlayers(this.players);
		this.gameRepository.save(expectedGame);
		
		Game actualGame = this.gameRepository.findById(gameId).get();
		assertEquals(expectedGame, actualGame);	
	}
}
