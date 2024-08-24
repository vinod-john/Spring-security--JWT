package com.john.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.john.entities.User;
import com.john.entities.UserPricipal;
import com.john.repo.UserRepo;

@Service
public class CustomeUserdetailService implements UserDetailsService {

	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = repo.findByUsername(username);
                if(user==null) {
                	throw new UsernameNotFoundException("UserNotFound"+username);
                }
		return new UserPricipal(user);
	}

}
