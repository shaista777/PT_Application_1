package com.ptapp.pt.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ptapp.pt.BCryptPass;
import com.ptapp.pt.model.Users;
import com.ptapp.pt.repository.UserRepository;
import com.ptapp.pt.status.Status;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/users/testIP")
	public Status viewUser() {
		return Status.SUCCESS;
	}

	@PostMapping("/users/register")
	public Status registerUser(@Valid @RequestBody Users newUser) {
        
		newUser.setPassword(new BCryptPass().hashPassword(newUser.getPassword()));
		if (userRepository.findByUserName(newUser.getUserName()).isEmpty()) {
			userRepository.save(newUser);
			return Status.SUCCESS;

		}
		userRepository.save(newUser);
		return Status.UPDATE_SUCCESS;

	}
	


}
