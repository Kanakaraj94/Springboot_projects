package com.arkatiss.authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkatiss.authentication.model.User;
import com.arkatiss.authentication.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Object getAllUsers() {
		return userRepository.findAll();
	}

	public Object addUser(User user) {
		return userRepository.save(user);
	}
}
