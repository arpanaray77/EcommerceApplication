package com.ecommerce.userservice.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.userservice.Controller.DTO.UserRegistrationDto;
import com.ecommerce.userservice.Model.Roles;
import com.ecommerce.userservice.Model.User;
import com.ecommerce.userservice.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}
	
	@Override
	public User save(UserRegistrationDto registrationDto) throws Exception
	{
		if(userRepository.existsByEmail(registrationDto.getEmail())){
		   throw new Exception("User is already existing!!");
		}
		User user =new User(registrationDto.getUsername(),registrationDto.getEmail(),passwordEncoder.encode(registrationDto.getPassword()), 
				Arrays.asList(new Roles("ROLE_USER")));
		
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userRepository.findByEmail(username);
		 if(user == null)
		 {
			 throw new UsernameNotFoundException("Invalid username or password");
		 }
		 
		 return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRolename())).collect(Collectors.toList());
	}

}
