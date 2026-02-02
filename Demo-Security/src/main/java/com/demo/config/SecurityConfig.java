package com.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	AuthTokernFilter authTokernFilter;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.
		csrf(csrf->csrf.disable())
		.authorizeHttpRequests(any-> 
				any.requestMatchers("/get/user/**").hasRole("USER")
				.requestMatchers("/admin/get/**").hasRole("ADMIN")
				.requestMatchers("/login").permitAll()
				.anyRequest()
				.authenticated());
		http.addFilterBefore(authTokernFilter, UsernamePasswordAuthenticationFilter.class);
//		.httpBasic(Customizer.withDefaults());
		
		return http.build();
	}

	@Bean
	UserDetailsService userDetailsService() {
		
		UserDetails user = User.withUsername("ajay")
				.password(passwordEncoder().encode("ajay123")) 
				.roles("USER")
				.build();
		
		UserDetails user1 = User.withUsername("rahul")
				.password(passwordEncoder().encode("rahul123")) 
				.roles("USER")
				.build();
		
		UserDetails user2 = User.withUsername("pravin")
				.password(passwordEncoder().encode("pravin123")) 
				.roles("ADMIN")
				.build();
		
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(datasource);
		
		if( !manager.userExists(user.getUsername())) {
			manager.createUser(user);		
		}
		
		if( !manager.userExists(user1.getUsername())) {
			manager.createUser(user1);		
		}
		
		if( !manager.userExists(user2.getUsername())) {
			manager.createUser(user2);		
		}
		
		return manager;
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager( AuthenticationConfiguration builder) throws Exception {
		
		return builder.getAuthenticationManager();
	}
	
}
