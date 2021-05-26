package com.ecommerce.userservice.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.userservice.Model.User;

public class CustomUserDetail extends User implements UserDetails{

	private static final long serialVersionUID = 1L;
	
	public CustomUserDetail(User user) {
          super();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorityList =new ArrayList<>();
		super.getRoles().forEach(role-> {
			authorityList.add(new SimpleGrantedAuthority(role.getRolename()));
		});
		return authorityList;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {	
		return super.getUsername();
		}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
