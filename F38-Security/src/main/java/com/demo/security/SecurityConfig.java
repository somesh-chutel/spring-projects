package com.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	AuthTokenFilter authTokenFilter;
	
	@Autowired
	DataSource datasource;
	
	
	@Bean	
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.
		csrf(csrf -> csrf.disable())
		.authorizeHttpRequests( auth -> auth
		.requestMatchers("/user/get/**").hasAnyRole("USER","ADMIN")
		.requestMatchers("/admin/get/**").hasAnyRole("ADMIN")
		.requestMatchers("/login").permitAll()
		.anyRequest().authenticated());
		http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
//		.httpBasic(Customizer.withDefaults());
		
		return http.build(); 
		
	}
	
	@Bean
	UserDetailsService userDetailsService() {
		
		UserDetails user = User.withUsername("rajesh")
				.password(passwordEncoder().encode("rajesh123"))
				.roles("USER").build();
		UserDetails user1 = User.withUsername("rahul")
				.password(passwordEncoder().encode("rahul123"))
				.roles("ADMIN").build();
		
//		return new InMemoryUserDetailsManager(user,user1,user2);
		
		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(datasource);
		
//		userDetailsManager.createUser(user);
//		userDetailsManager.createUser(user1);
		
		return userDetailsManager;
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		
		return builder.getAuthenticationManager();
	}

}
