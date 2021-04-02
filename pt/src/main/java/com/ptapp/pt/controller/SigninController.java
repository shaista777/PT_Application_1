package com.ptapp.pt.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ptapp.pt.BCryptPass;
import com.ptapp.pt.model.Users;
import com.ptapp.pt.repository.UserRepository;
import com.ptapp.pt.status.Status;

@RestController
public class SigninController {
	@Autowired
	UserRepository userRepository;
	
    
    
	@PostMapping("/users/login")
	public Status loginUser(@Valid @RequestBody Users user) {
	
		List<Users> users = userRepository.findByUserName(user.getUserName());
		
		if (users.isEmpty() || new BCryptPass().matches(user.getPassword(),users.get(0).getPassword()) == false)
			return Status.FAILURE;
		
		if (users.get(0).isLoggedIn())
			return Status.SESSION_EXISTS;
		
		users.get(0).setPassword(new BCryptPass().hashPassword(user.getPassword()));
		users.get(0).setLoggedIn(true);
		userRepository.save(users.get(0));
		return Status.SUCCESS;

	}

	@PostMapping("/users/logout")
	public Status logUserOut(@Valid @RequestBody Users user) {
		List<Users> users = userRepository.findByUserName(user.getUserName());

		if (users.isEmpty())
			return Status.FAILURE;
		
		if (users.get(0).isLoggedIn() && new BCryptPass().matches(user.getPassword(),users.get(0).getPassword())) {
			users.get(0).setLoggedIn(false);
			users.get(0).setPassword(new BCryptPass().hashPassword(user.getPassword()));
			userRepository.save(users.get(0));
			return Status.SUCCESS;
		}

		return Status.SESSION_NOT_FOUND;
	}
	

}
