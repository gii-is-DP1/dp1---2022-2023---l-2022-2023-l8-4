package org.springframework.samples.petclinic.carta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cartas")
public class CartaControlador {
	@Autowired
	private CartaServicio boardsService;
}
