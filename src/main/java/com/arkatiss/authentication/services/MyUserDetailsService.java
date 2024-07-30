package com.arkatiss.authentication.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arkatiss.authentication.repo.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		return new User("foo","foo",new ArrayList<>());
//	}
	
	@Override
    public UserDetails loadUserByUsername(String username) {
        com.arkatiss.authentication.model.User user1 = repository.findByUsername(username);
        System.out.println("user--> "+user1);
        if (user1 == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user1.getUsername(),user1.getPassword(),new ArrayList<>());
    }
	
	

}
