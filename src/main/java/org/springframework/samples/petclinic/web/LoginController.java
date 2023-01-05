package org.springframework.samples.petclinic.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLoginForm() {
		
		return "login";
	}
	
	@GetMapping("/logout")
	public String getLogoutForm() {
		
		return "logout";
	}
}
