package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.LoginDetails;
import com.demo.security.UtilsJwt;

@RestController
public class MainController {
	
	@Autowired
	private UtilsJwt jwtUtils;
	
	@Autowired 
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/user/get")
	public String getResponse() {
		
		return "Hello USER";
	}
	
	@GetMapping("/admin/get")
	public String getAdminResponse() {
		
		return "Hello ADMIN";
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestBody LoginDetails loginDetails) {
		
		Authentication authentication;
		
		try {
			
			authentication = authenticationManager.authenticate(
					
					new UsernamePasswordAuthenticationToken(
							
							loginDetails.getUsername(),
							loginDetails.getPassword()
							
							)
					
					);
					
			
			
		} catch (Exception e) {
			
			e.getStackTrace();
			
			return "Invalid Credintial";
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String jwtToken = jwtUtils.getJwtTokenFromStorage(userDetails);
		
		return jwtToken;
	}

}
