package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.usuario.Usuario;

@DataJpaTest
public class PartidaServicioTest {
	
	@Autowired
	private PartidaRepositorio partidaRepositorio;
	
	private Collection<Usuario> jugadores;
	
	@BeforeEach
	void setup() {
		Usuario usuario1 = new Usuario();
		usuario1.setId(7);
		Usuario usuario2 = new Usuario();
		usuario2.setId(8);
		Usuario usuario3 = new Usuario();
		usuario3.setId(9);
		
		this.jugadores = List.of(usuario1, usuario2, usuario3);
	}
	
	@Test
	void debeBuscarPartidaConIdCorrecto() {
		int partidaId = 1;
		Partida partida = new Partida();
		partida.setId(partidaId);
		partida.setModo(Modo.ESTANDAR);
		partida.setFecha(Date.valueOf(LocalDate.of(2022, 11, 4)));
		Optional<Partida> partidaEsperada = Optional.of(partida);
		
		Optional<Partida> partidaObtenida = this.partidaRepositorio.findById(partidaId);
		assertEquals(partidaEsperada, partidaObtenida, "La partida no coincide");
	}
	
	@Test
	void noDebeEncontrarPartida() {
		int partidaId = Integer.MIN_VALUE;
		Optional<Partida> partidaEsperada = Optional.empty();
		
		Optional<Partida> partidaObtenida = this.partidaRepositorio.findById(partidaId);
		
		assertEquals(partidaEsperada, partidaObtenida, "El metodo no debe encontrar ninguna partida con ese id");
	}

	@Test
	void debeCrearPartida() {
		
		Partida partidaEsperada = new Partida();
		int partidaId = 6;
		partidaEsperada.setId(partidaId);
		partidaEsperada.setFecha(Date.valueOf(LocalDate.now()));
		partidaEsperada.setModo(Modo.EL_FOSO);
		partidaEsperada.setJugadores(jugadores);
		this.partidaRepositorio.save(partidaEsperada);
		assertEquals(partidaEsperada, this.partidaRepositorio.findById(partidaId));	
	}

}
