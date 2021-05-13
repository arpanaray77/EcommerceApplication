package com.ecommerce.userservice.Service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ecommerce.userservice.Controller.DTO.UserRegistrationDto;
import com.ecommerce.userservice.Model.User;

public interface UserService extends UserDetailsService{

	User save(UserRegistrationDto registrationdto);
}
