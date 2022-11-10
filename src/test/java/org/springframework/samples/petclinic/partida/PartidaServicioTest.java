package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.samples.petclinic.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class PartidaServicioTest {
	
	@Autowired
	protected PartidaServicio partidaServicio;
	
	@Autowired
	protected UsuarioService usuarioServicio;

	
	@Test
	void debeBuscarPartidaConIdCorrecto() {
		Partida partida = this.partidaServicio.getPartidaPorId(1);
		assertEquals(Modo.ESTANDAR, partida.getModo(),"El modo de juego no coincide con el de la BD");
	}
	
	@Test
	void debeGuardarPartidaCreadaPorJugador() {
		// El id de la partida depende de los insert de partida en data.sql
		int usuarioId = 1;
		Usuario usuario = this.usuarioServicio.getUsuarioPorId(usuarioId);
		
		// El id de la partida depende de los insert de partida en data.sql
		int partidaId = 2;
		Partida partida = new Partida();
		partida.setCreadorPartida(usuario);
		partida.setFecha(new Date());
		partida.setModo(Modo.EL_FOSO);
		
		try {
			this.partidaServicio.savePartida(partida);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Partida actual = this.partidaServicio.getPartidaPorId(partidaId);
		assertEquals(partida.getId(), actual.getId(), "No es la misma partida");
		assertEquals(usuario.getNombreUsuario(), actual.getCreadorPartida().getNombreUsuario(), "Los nombres de usuario no coinciden");
	}
}
