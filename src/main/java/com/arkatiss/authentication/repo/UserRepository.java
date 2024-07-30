package com.arkatiss.authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arkatiss.authentication.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

}
