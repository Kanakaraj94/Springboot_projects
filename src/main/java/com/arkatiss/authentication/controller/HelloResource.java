package com.arkatiss.authentication.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arkatiss.authentication.model.AuthenticationRequests;
import com.arkatiss.authentication.model.AuthenticationResponse;
import com.arkatiss.authentication.services.MyUserDetailsService;
import com.arkatiss.authentication.util.JwtUtil;

@RestController
public class HelloResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService UserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/hello")
	public String hello() {
		return "Hello World";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequests authenticationRequests,HttpServletRequest request) throws Exception{
		try {
			
			
			CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
			csrfToken.getHeaderName();
			csrfToken.getToken();
			System.out.println("Header: " + csrfToken.getHeaderName());
			System.out.println("CSRF-TOKEN: " + csrfToken.getToken());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequests.getUsername(), authenticationRequests.getPassword())
				);
		}catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserDetails userDetails = UserDetailsService
				.loadUserByUsername(authenticationRequests.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
