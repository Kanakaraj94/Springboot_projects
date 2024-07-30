package com.arkatiss.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableSwagger2
public class CsrfTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsrfTokenApplication.class, args);
	}

}
