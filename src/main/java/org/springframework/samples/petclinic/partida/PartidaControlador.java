package org.springframework.samples.petclinic.partida;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/partidas")
public class PartidaControlador {
	
	private static final String WELCOME = "welcome";
	private static final String VISTA_PARTIDA_FORMULARIO_CREACION = "partidas/crearPartida";
	private static final String VISTA_PARTIDA_LISTA = "partidas/listaPartidas";
	private PartidaServicio partidaServicio;
	
	@Autowired
	public PartidaControlador(PartidaServicio partidaServicio) {
		this.partidaServicio = partidaServicio;
	}
	
	@GetMapping(value = "/new")
	public String iniciarFormulario(Map<String, Object> model) {
		Partida partida = new Partida();
		model.put("partida", partida);
		return VISTA_PARTIDA_FORMULARIO_CREACION;
	}

	@PostMapping(value = "/new")
	public String procesarForlulario(@Valid Partida partida, BindingResult result) throws DataAccessException, Exception {
		if (result.hasErrors()) {
			return VISTA_PARTIDA_FORMULARIO_CREACION;
		}
		else {
			this.partidaServicio.savePartida(partida);
			return "redirect:/partidas/" + partida.getId();
		}
	}
	
	@GetMapping("/{partidaId}")
	public ModelAndView mostrarPartida(@PathVariable("partidaId") int partidaId) {
		ModelAndView mav = new ModelAndView("partidas/detallesPartida");
		mav.addObject(this.partidaServicio.getPartidaPorId(partidaId));
		return mav;
	}
	
	@GetMapping(value = "/")
	public String listarPartidas(Partida partida, BindingResult result, Map<String, Object> model) {
		List<Partida> partidas = partidaServicio.getPartidas();
		model.put("partidas", partidas);
		return VISTA_PARTIDA_LISTA;
		
	}
	
}
