package org.springframework.samples.petclinic.card;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/cards")
public class CardController {
	@Autowired
	private CardService cardService;

	@GetMapping
	public ModelAndView cardsList(){
		ModelAndView result=new ModelAndView("ListOfCards");
		result.addObject("cards", cardService.getDeck());
		return result;
	}

	@GetMapping("/cards/{cardId}")
	public ModelAndView mostrarCarta(@PathVariable("cardId") int cardId) {
		ModelAndView mav = new ModelAndView("cards/cardDetails");
		mav.addObject(this.cardService.findCardById( cardId ) );
		return mav;
	}
}
