package com.ecommerce.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
//		 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		    String password [] = {"admin"};
//		    for(int i = 0; i < password.length; i++)
//		        System.out.println(passwordEncoder.encode(password[i]));
  }
}
