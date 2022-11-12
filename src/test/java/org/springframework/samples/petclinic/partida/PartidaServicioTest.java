package org.springframework.samples.petclinic.partida;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.jugador.Jugador;
import org.springframework.stereotype.Service;


@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class PartidaServicioTest {
	

	@Autowired
	private PartidaServicio partidaServicio;
	
	
	@Test
	public void debeBuscarJugadoresPartida(){
		try {
			Collection<Jugador> jugadores = this.partidaServicio.getJugadoresPartida(1);
			List<Jugador> jugadoresPartida = new ArrayList<Jugador>(jugadores);
			String[] usernameJugadores = {"pgmarc","carbersor"};

			
			int numeroJugadoresEsperados = 2;
			
			assertEquals(numeroJugadoresEsperados, jugadoresPartida.size());
			
			for (int i = 0; i < jugadoresPartida.size(); i++) {
				assertEquals(usernameJugadores[i], jugadoresPartida.get(i).getUser().getUsername());
			}
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void noDebeEncontrarJugadoresPartida() {
		 Exception exception = assertThrows(Exception.class, () -> { 
			 	this.partidaServicio.getJugadoresPartida(-1);
			 });

		    String mensajeEsperado = "Partida no encontrada";
		    String mensajeObtenido = exception.getMessage();
		   

		    assertTrue(mensajeEsperado.contains(mensajeObtenido));
	}
}
