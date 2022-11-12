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
public class PartidaServicioTest {
	
	@Autowired
	private GameRepository gameRepository;
	
	private Collection<Player> jugadores;
	
	@BeforeEach
	void setup() {
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		
		this.jugadores = List.of(player1, player2, player3);
	}
	
	@Test
	void debeBuscarPartidaConIdCorrecto() {
		int partidaId = 1;
		Game partida = new Game();
		partida.setId(partidaId);
		partida.setGameMode(GameMode.ESTANDAR);
		partida.setDate(LocalDate.of(2022, 11, 4));
		Optional<Game> partidaEsperada = Optional.of(partida);
		
		Optional<Game> partidaObtenida = this.gameRepository.findById(partidaId);
		assertEquals(partidaEsperada.get().getId(), partidaObtenida.get().getId(), "La partida no coincide");
		assertEquals(partidaEsperada.get().getDate(), partidaObtenida.get().getDate(), "La partida no coincide");
		assertEquals(partidaEsperada.get().getGameMode(), partidaObtenida.get().getGameMode(), "La partida no coincide");

	}
	
	@Test
	void noDebeEncontrarPartida() {
		int partidaId = Integer.MIN_VALUE;
		Optional<Game> partidaEsperada = Optional.empty();
		
		Optional<Game> partidaObtenida = this.gameRepository.findById(partidaId);
		
		assertEquals(partidaEsperada, partidaObtenida, "El metodo no debe encontrar ninguna partida con ese id");
	}

	@Test
	void debeCrearPartida() {
		
		Game partidaEsperada = new Game();
		int partidaId = 6;
		partidaEsperada.setId(partidaId);
		partidaEsperada.setDate(LocalDate.now());
		partidaEsperada.setGameMode(GameMode.EL_FOSO);
		partidaEsperada.setPlayers(this.jugadores);
		this.gameRepository.save(partidaEsperada);
		
		Game partidaObtenida = this.gameRepository.findById(partidaId).get();
		assertEquals(partidaEsperada, partidaObtenida);	
	}

}
