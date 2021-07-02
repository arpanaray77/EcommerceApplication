package com.ecommerce.userservice.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.ecommerce.userservice.Service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserService userService;
	
	@Autowired
    GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	//for identifying jpa n mysql
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
       auth.setUserDetailsService((UserDetailsService) userService);
       auth.setPasswordEncoder(passwordEncoder());
      return auth;
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.authenticationProvider(authenticationProvider());
	   auth.userDetailsService(userService);
        }
	
	 @Override 
	 protected void configure(HttpSecurity http) throws Exception
	 {
	     http
	     .csrf().disable()
	     .authorizeRequests()
	     .antMatchers(
	    		 "/",
	    		 "/shop/**",
	    		 "/registration**").permitAll()
	     .antMatchers("/admin/**").hasRole("ADMIN")
	     .anyRequest().authenticated()
	     .and()
	     .formLogin()
	     .loginPage("/login").permitAll()
	     .failureUrl("/login?error=true")
	     .defaultSuccessUrl("/shop/{id}")
	     .usernameParameter("email")
	     .passwordParameter("password")
	     .and()
	     .oauth2Login()
	     .loginPage("/login")
	     .successHandler(googleOAuth2SuccessHandler)
	     .and()
	     .logout()
	     .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	     .logoutSuccessUrl("/login")
	     .invalidateHttpSession(true)
	     .clearAuthentication(true)
	     .deleteCookies("JSESSIONID")
	     .and()
	     .exceptionHandling();
	     
	     
	   http.headers().frameOptions().disable();  
	     
	 }
	 
	 @Override
	public void configure(WebSecurity web) throws Exception
	 {
		 web.ignoring().antMatchers("/resources/**","/static/**","/img/**","/productImages/**","/css/**","/js/**");
	 }
	 
	 
}
