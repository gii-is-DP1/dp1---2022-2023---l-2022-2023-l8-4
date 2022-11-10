package org.springframework.samples.petclinic.carta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/cartas")
public class CartaControlador {

	private CartaServicio cartaServicio;
	
	@Autowired
	public CartaControlador(CartaServicio cartaServicio) {
		this.cartaServicio = cartaServicio;
	}
	
	@GetMapping
	public ModelAndView listadoCartas(){		
		ModelAndView result=new ModelAndView("ListaDeCartas");		
		result.addObject("cartas", cartaServicio.getBaraja());
		return result;
	}
	
	
	//----------puede que necesitemos encontrar las cartas por ID------------
	
	/*
	@GetMapping("/cartas/{cartaId}")
	public ModelAndView mostrarCarta(@PathVariable("cartaId") int cartaId) {
		ModelAndView mav = new ModelAndView("cartas/cartaDetalles");
		mav.addObject(this.cartaServicio.findOwnerById(cartaId));
		return mav;
	}*/
}
