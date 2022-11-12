package org.springframework.samples.petclinic.partida;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PartidaControlador.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration= SecurityConfiguration.class)
public class PartidaControladorMockedTest {
	
	@MockBean
	private PartidaServicio partidaServicio;
	
	@Autowired
	private MockMvc mockMvc;
	
	private Integer partidaId = 1;
	
	@BeforeEach
	private void setup() {
		Partida partida=new Partida();
        partida.setId(partidaId);
        //Mockito.when(partidaServicio.savePartida(any(Partida.class))).thenReturn(null);
        Mockito.when(partidaServicio.getPartidaPorId(partidaId)).thenReturn(partida);
        List<Partida> partidas=new ArrayList<Partida>();
        partidas.add(partida);
        Mockito.when(partidaServicio.getPartidas()).thenReturn(partidas);
	}
	
	@Test
	void test() throws Exception {
		debeMostrarPartida();
		debeMostrarListaPartidas();
		debeIniciarFormularioPartida();		
	}
	
	
	void debeMostrarPartida() throws Exception{
		mockMvc.perform(get("/partidas/"+partidaId))
		.andExpect(status().isOk())
		.andExpect(view().name("partidas/detallesPartida"))
		.andExpect(model().attributeExists("partida"))
		.andExpect(model().attribute("partida", partidaServicio.getPartidaPorId(partidaId)));
	}
	
	void debeMostrarListaPartidas() throws Exception{
		mockMvc.perform(get("/partidas/"))
		.andExpect(status().isOk())
		.andExpect(view().name("partidas/listaPartidas"))
		.andExpect(model().attributeExists("partidas"))
		.andExpect(model().attribute("partidas", partidaServicio.getPartidas()));
	}
	
	void debeIniciarFormularioPartida() throws Exception{
		mockMvc.perform(get("/partidas/create"))
		.andExpect(status().isOk())
		.andExpect(view().name("partidas/crearPartida"))
        .andExpect(model().attributeExists("partida"));
	}
	
	
	
}
