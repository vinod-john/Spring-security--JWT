package com.john.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.john.entities.User;
import com.john.repo.UserRepo;

@Service
public class Userservice {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	JwtService service;
	
	@Autowired
	private AuthenticationManager manager;
	
	private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
	
	public User register(User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		return repo.save(user);
	}

	public String verify(User user) {
		Authentication authentication=manager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			return service.generteToken(user.getUsername());
		}
		return "Failed";
	}

}
