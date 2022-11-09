package org.springframework.samples.petclinic.carta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/cartas")
public class CartaControlador {
	/*
	@Autowired
	private CartaServicio cartaServicio;
	
	@GetMapping
	public ModelAndView listadoCartas(){		
		ModelAndView result=new ModelAndView("ListaDeCartas");		
		result.addObject("cartas", cartaServicio.findAll());
		return result;
	}
	
	@GetMapping("/cartas/{cartaId}")
	public ModelAndView mostrarCarta(@PathVariable("cartaId") int cartaId) {
		ModelAndView mav = new ModelAndView("cartas/cartaDetalles");
		mav.addObject(this.cartaServicio.findOwnerById(cartaId));
		return mav;
	}
	*/
}
