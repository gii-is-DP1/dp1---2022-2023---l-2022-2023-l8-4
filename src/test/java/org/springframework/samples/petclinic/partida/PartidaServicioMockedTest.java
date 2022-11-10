package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;

@ExtendWith(MockitoExtension.class)
class PartidaServicioMockedTest {
	
	@Mock
	private PartidaRepositorio partidaRepositorio;
	
	@Mock
	private UsuarioService usuarioServicio;
	
	private PartidaServicio partidaServicio;
	
	
	@BeforeEach
	private void setup() {
		this.partidaServicio = new PartidaServicio(partidaRepositorio, usuarioServicio);
	}
	
	@Test
	void debeBuscarPartidaConIdCorrecto() {
		int partidaId = 1;
		Partida partida1 = new Partida();
		partida1.setId(partidaId);
		partida1.setModo(Modo.EL_FOSO);
		Optional<Partida> partida = Optional.of(partida1);
		
		when(this.partidaRepositorio.findById(partidaId)).thenReturn(partida);
		Partida partidaEsperada = partidaServicio.getPartidaPorId(partidaId);
		assertEquals(partida.get(), partidaEsperada, "La partida no coincide");
	}
	
	@Test
	void noDebeEncontrarPartidaIdIncorrecto() {
		int partidaId = Integer.MIN_VALUE;
		Optional<Partida> stubPartida = Optional.empty();
		
		when(this.partidaRepositorio.findById(partidaId)).thenReturn(stubPartida);
		Partida partidaEsperada = this.partidaServicio.getPartidaPorId(partidaId);
		
		assertNull(partidaEsperada, "El metodo no debe encontrar ninguna partida con ese id");
	}
	
	@Test
	void debeGuardarPartidaCreadaPorJugador() {
		
		Usuario usuario = new Usuario();
		int usuarioId = 1;
		usuario.setId(usuarioId);
		usuario.setAdministrador(false);
		
		Partida partida = new Partida();
		int partidaId = 6;
		partida.setId(partidaId);
		partida.setCreadorId(usuario);
		partida.setFecha(new Date());
		partida.setModo(Modo.EL_FOSO);
		
		try {
			when(this.usuarioServicio.getUsuarioPorId(usuarioId)).thenReturn(usuario);
			this.partidaServicio.savePartida(partida);
			verify(this.partidaRepositorio).save(partida);
		} catch (DataAccessException e) {
			fail("La partida se debe haber guardado");
		} catch (Exception e) {
			fail("La partida se debe haber guardado");
		}
	}
}
