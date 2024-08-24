package com.john.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.john.entities.User;
import com.john.service.Userservice;

@RestController
public class UserController {
	
	@Autowired
	private Userservice service;
	
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return service.register(user);
	}
	@PostMapping("/login")
	public String Login(@RequestBody User user) {
		return service.verify(user);
	}

}
