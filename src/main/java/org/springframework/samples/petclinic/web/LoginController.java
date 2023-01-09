package org.springframework.samples.petclinic.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String getLoginForm() {
		
		SecurityContextHolder.clearContext();
		
		return "login";
	}
	
	@GetMapping("/logout")
	public String getLogoutForm() {
		
		return "logout";
	}
}
