package com.brhmkskn.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.brhmkskn.exception.BaseException;
import com.brhmkskn.exception.ErrorMessage;
import com.brhmkskn.exception.MessageType;
import com.brhmkskn.model.User;
import com.brhmkskn.repository.IUserRepository;

@Configuration
public class AppConfig {

	@Autowired
	private IUserRepository userRepository;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Optional<User> optional= userRepository.findByUsername(username);
				if(optional.isEmpty())
				{
					throw new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, username));
				}
				
				return optional.get();
			}
		};
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider(userDetailsService());
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return authenticationProvider;
		
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}