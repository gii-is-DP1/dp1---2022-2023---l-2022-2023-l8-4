package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.usuario.Usuario;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
class PartidaServicioTest {
	
	@Autowired
	protected PartidaServicio partidaServicio;

	
	@Test
	void debeBuscarPartidaIdCorrecto() {
		Partida partida = this.partidaServicio.getPartidaPorId(1);
		assertEquals(Modo.ESTANDAR, partida.getModo(),"El modo de juego no coincide con el de la BD");
	}
	
	@Test
	void debeGuardarPartida() {
		Date fechaActual = new Date();
		Usuario usuario = new Usuario();
		usuario.setId(2);
		usuario.setEsAdministrador(false);
		usuario.setContraseña("aaaa");
		usuario.setNombreUsuario("elrichmc");
		usuario.setEmail("elr@mail.com");
		usuario.setFechaModificacion(fechaActual);
		usuario.setFechaNacimiento(fechaActual);
		usuario.setFechaRegistro(fechaActual);
		usuario.setUltimoInicioSesion(fechaActual);
		usuario.setFotoPerfil("Foto perfil");
			
		int partidaId = 2;
		Partida partida = new Partida();
		partida.setId(partidaId);
		partida.setCreadorId(usuario);
		partida.setFecha(new Date());
		partida.setModo(Modo.EL_FOSO);
		
		this.partidaServicio.savePartida(partida);
		
		Partida actual = this.partidaServicio.getPartidaPorId(partidaId);
		
		assertEquals(partida, actual, "No es la misma partida");
		assertEquals(partida.getCreadorId().getName(), actual.getCreadorId().getName());
	}

}
