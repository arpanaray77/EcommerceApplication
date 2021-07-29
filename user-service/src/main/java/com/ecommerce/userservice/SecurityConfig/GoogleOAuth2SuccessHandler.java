package com.ecommerce.userservice.SecurityConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.ecommerce.userservice.Model.Roles;
import com.ecommerce.userservice.Model.User;
import com.ecommerce.userservice.Repository.RoleRepository;
import com.ecommerce.userservice.Repository.UserRepository;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy(); 

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		    OAuth2AuthenticationToken token= (OAuth2AuthenticationToken) authentication;
		    String email=token.getPrincipal().getAttributes().get("email").toString();
		    if(userRepository.findByEmail(email).isPresent())
		    {
		    	
		    }
		    else {
		    	User user=new User();
		    	user.setUsername(token.getPrincipal().getAttributes().get("given_name").toString());
		    	user.setEmail(email);
		    	List<Roles> roles=new ArrayList<>();
		    	roles.add(roleRepository.findById(1).get());
		    	user.setRoles(roles);
		    	userRepository.save(user);
		    }
		    
		    redirectStrategy.sendRedirect(request, response, "/");	
	
	}	
	
}
