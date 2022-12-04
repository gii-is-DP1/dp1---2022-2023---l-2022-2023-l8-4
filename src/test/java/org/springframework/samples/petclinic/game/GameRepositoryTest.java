package org.springframework.samples.petclinic.game;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.samples.petclinic.player.Player;


@DataJpaTest
public class GameRepositoryTest {
	
	@Autowired
	private GameRepository gameRepository;
	
	private List<Game> games;
	
	private int numberOfGames;
	
	
	@BeforeEach
	void setup() {
		this.games = this.gameRepository.findAll();
		this.numberOfGames = this.games.size();
	}
	
	@Test
	void shouldFindGameCorrectId() {
		int gameId = 1;
		Game firstGame = games.get(0);
		Game actualGame = this.gameRepository.findById(gameId).get();
		
		assertEquals(firstGame.getId(), actualGame.getId(), "The game id does not match");
		assertEquals(firstGame.getDate(), actualGame.getDate(), "The game date does not match");
		assertEquals(firstGame.getGameMode(), actualGame.getGameMode(), "Game mode does not match");
		assertEquals(firstGame.getGameState(), actualGame.getGameState(), "The state of the game does not match");
		assertEquals(firstGame.getGameCode(), actualGame.getGameCode(), "The game code does not match");

	}
	
	@Test
	void shouldNotFindGameInvalidId() {
		int gameId = Integer.MIN_VALUE;
		Optional<Game> expectedGame = Optional.empty();
		
		Optional<Game> actualGame = this.gameRepository.findById(gameId);
		
		assertEquals(expectedGame, actualGame, "It should not find the game");
	}
	
	private Game createGame() {
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Collection<Player> players = List.of(player1, player2, player3);
		
		Game game = new Game();
		int gameId = this.numberOfGames + 1;
		int gameCode = 15;
		game.setId(gameId);
		game.setDate(LocalDate.now());
		game.setGameMode(GameMode.EL_FOSO);
		game.setGameCode(gameCode);
		game.setGameState(GameState.INITIATED);
		game.setGameCode(15);
		game.setPlayers(players);
		
		return game;
	}

	@Test
	void shouldSaveGame() {
		
		Game expectedGame = createGame();
		
		this.gameRepository.save(expectedGame);
		
		Game actualGame = this.gameRepository.findById(expectedGame.getId()).get();
		assertEquals(expectedGame.getId(), actualGame.getId());
		assertEquals(expectedGame.getDate(), actualGame.getDate());	
		assertEquals(expectedGame.getGameMode(), actualGame.getGameMode());	
		assertEquals(expectedGame.getGameCode(), actualGame.getGameCode());	
		assertEquals(expectedGame.getPlayers(), actualGame.getPlayers());	
	}
	
	private List<Game> getGamesWithStateOrderedByDateDesc(GameState gameState) {
		return gameRepository.findAll().stream().filter(game -> game.getGameState().equals(gameState))
				.sorted(Comparator.comparing(Game::getDate).reversed()).collect(Collectors.toList());	
	}
	
	@Test
	public void shouldGetGamesInitiated(){
		List<Game> gamesInitiated = getGamesWithStateOrderedByDateDesc(GameState.INITIATED);
		Page<Game> actualGamesInitiated = this.gameRepository.findGamesByGameStateOrderByDateDesc(PageRequest.of(0, 
				gamesInitiated.size()), GameState.INITIATED);
			
		assertNotNull(actualGamesInitiated, "The list of games cannot be null");
		assertFalse(actualGamesInitiated.isEmpty());
		assertEquals(gamesInitiated.size(), actualGamesInitiated.getContent().size(), "Sizes do not match");
		
	}
	
	@Test
	public void shouldGetGamesInProgress(){
		List<Game> gamesInProgress = getGamesWithStateOrderedByDateDesc(GameState.IN_PROGRESS);
		
		Page<Game> actualGamesInProgress = this.gameRepository.findGamesByGameStateOrderByDateDesc(PageRequest.of(0, 
				gamesInProgress.size()), GameState.IN_PROGRESS);
			
		assertNotNull(actualGamesInProgress, "The list of games cannot be null");
		assertFalse(actualGamesInProgress.isEmpty());
		assertEquals(gamesInProgress.size(), actualGamesInProgress.getContent().size(), "Sizes do not match");
	}
	
	@Test
	public void shouldGetGamesFinalized(){
		List<Game> gamesFinalized = getGamesWithStateOrderedByDateDesc(GameState.FINALIZED);
		
		Page<Game> actualGamesFinalized = this.gameRepository.findGamesByGameStateOrderByDateDesc(PageRequest.of(0, 
				gamesFinalized.size()), GameState.FINALIZED);
			
		assertNotNull(actualGamesFinalized, "The list of games cannot be null");
		assertFalse(actualGamesFinalized.isEmpty());
		assertEquals(gamesFinalized.size(), actualGamesFinalized.getContent().size(), "Sizes do not match");
		
	}
	
	@Test
	public void shouldNotGetGamesWithNullState(){
		
		Page<Game> actualGamesFinalized = this.gameRepository.findGamesByGameStateOrderByDateDesc(PageRequest.of(0, 5), null);
			
		assertNotNull(actualGamesFinalized, "The list of games cannot be null");
		assertTrue(actualGamesFinalized.isEmpty(), "The list should be empty");
		
	}
}
