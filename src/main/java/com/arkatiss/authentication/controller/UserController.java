package com.arkatiss.authentication.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arkatiss.authentication.model.User;
import com.arkatiss.authentication.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/getUsers")
	public ResponseEntity<?> getAllusers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@RequestBody User user, HttpServletRequest request) {
		CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
		csrfToken.getHeaderName();
		csrfToken.getToken();
		System.out.println("Header: " + csrfToken.getHeaderName());
		System.out.println("CSRF-TOKEN: " + csrfToken.getToken());
		return ResponseEntity.ok(userService.addUser(user));
	}

//	@PostMapping("/addUser")
//	public ResponseEntity<?> addUser(@RequestBody User user,HttpServletRequest request){
//		return ResponseEntity.ok(userService.addUser(user));
//	}

}
