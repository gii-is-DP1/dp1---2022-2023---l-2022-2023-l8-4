package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.samples.petclinic.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	
	  @GetMapping({"/","/welcome"})
	  public String welcome(Map<String, Object> model) {
		  
		  List<Person> people = new ArrayList<Person>();
		  
		  
		Person pgmarc = new Person();
		Person carbersor= new Person();
		Person carlos = new Person();
		Person daniel = new Person();
		Person pedro = new Person();
		Person fernando = new Person();

		
		pgmarc.setFirstName("Pedro");
		pgmarc.setLastName("Gonzalez");
		people.add(pgmarc);
		
		carbersor.setFirstName("Carlos");
		carbersor.setLastName("Bermejo");
		people.add(carbersor);

		
		fernando.setFirstName("Fernando Jose");
		fernando.setLastName("Mateos");
		people.add(fernando);

		
		daniel.setFirstName("Daniel");
		daniel.setLastName("Gallardo");
		people.add(daniel);

		
		pedro.setFirstName("Pedro");
		pedro.setLastName("Lopez");
		people.add(pedro);

		
		carlos.setFirstName("Carlos");
		carlos.setLastName("Zarzuela");
		people.add(carlos);
		
		model.put("people", people);
		model.put("title", "petclinic");
		model.put("group", "l8-4");


	    return "welcome";
	}
}
