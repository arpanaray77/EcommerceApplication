package com.ecommerce.userservice.Service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.ecommerce.userservice.Controller.DTO.UserRegistrationDto;
import com.ecommerce.userservice.Model.Roles;
import com.ecommerce.userservice.Model.User;
import com.ecommerce.userservice.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	//@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public User save(UserRegistrationDto registrationDto)
	{
		User user =new User(registrationDto.getUsername(),registrationDto.getEmail(),registrationDto.getPassword(),
				Arrays.asList(new Roles("ROLE_USER")));
		
		return userRepository.save(user);
	}

}
