package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.springframework.samples.petclinic.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {
		  
		  List<Usuario> people = new ArrayList<Usuario>();
		  
		  
		Usuario pgmarc = new Usuario();
		Usuario carbersor= new Usuario();
		Usuario carlos = new Usuario();
		Usuario daniel = new Usuario();
		Usuario pedro = new Usuario();
		Usuario fernando = new Usuario();

		
		pgmarc.setName("Pedro");
		pgmarc.setUsername("");
		pgmarc.setEmail("");
		people.add(pgmarc);
		
		carbersor.setName("Carlos");
		carbersor.setUsername("");
		pgmarc.setEmail("");
		people.add(carbersor);

		
		fernando.setName("Fernando Jose");
		fernando.setUsername("");
		pgmarc.setEmail("");
		people.add(fernando);

		
		daniel.setName("Daniel");
		daniel.setUsername("");
		pgmarc.setEmail("");
		people.add(daniel);

		
		pedro.setName("Pedro");
		pedro.setUsername("Lopez");
		pgmarc.setEmail("");
		people.add(pedro);

		
		carlos.setName("Carlos Zarzuela");
		carlos.setUsername("");
		pgmarc.setEmail("");
		people.add(carlos);
		
		model.put("people", people);
		model.put("title", "Dobble");
		model.put("group", "l8-4");


	    return "welcome";
	}
}
