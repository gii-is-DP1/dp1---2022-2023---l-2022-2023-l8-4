/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.user;

import java.util.ArrayList; 
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.exception.NoSuchEntityException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class UserController {

	private static final String VIEWS_OWNER_CREATE_FORM = "users/createPlayer";

	private final PlayerService playerService;

	private final UserService userService;

	@Autowired
	public UserController(PlayerService clinicService, UserService userService) {
		this.playerService = clinicService;
		this.userService = userService;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = { "/admin/all" })
	public String showUserList(Map<String, Object> model) {
		Collection<User> users = new ArrayList<User>();
		users.addAll(this.userService.getAllAdmins());
		model.put("users", users);
		return "users/userList";
	}
	
	@GetMapping(value = { "/users.xml"})
	public @ResponseBody Collection<User> showResourcesUserList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Collection<User> users = new ArrayList<User>();
		users.addAll(this.userService.getAllAdmins());
		return users;
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		Player player = new Player();
		model.put("player", player);
		return VIEWS_OWNER_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid Player player, BindingResult result) throws DataAccessException, NoSuchEntityException {
		System.out.println(player.getUser());
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors());;
			return VIEWS_OWNER_CREATE_FORM;
		} else {
			// creating owner, user, and authority
			this.playerService.savePlayer(player);
			return "redirect:/";
		}
	}

}
