package com.john.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.john.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	User findByUsername(String username);

}
