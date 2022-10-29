package org.springframework.samples.petclinic.carta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/cartas")
public class CartaControlador {
	@Autowired
	private CartaServicio cartaServicio;
	
	@GetMapping
	public ModelAndView listadoCartas(){		
		ModelAndView result=new ModelAndView("ListaDeCartas");		
		result.addObject("cartas", cartaServicio.findAll());
		return result;
	}
}
