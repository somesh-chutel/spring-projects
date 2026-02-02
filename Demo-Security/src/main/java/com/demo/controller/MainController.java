package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.config.JwtUtils;
import com.demo.dto.LoginDetails;

@RestController
public class MainController {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils utlis;

	@GetMapping("/")
	public String getNewString() {

		return "Hello New";
	}

	@GetMapping("/get")
	public String getLocalResponse() {

		return "Hello";
	}

	@GetMapping("/admin/get")
	public String getAdminResponse() {
		return "Hello Admin";
	}

	@GetMapping("/user/get")
	public String getUserResponse() {
		return "Hello User";
	}

	@PostMapping("/login")
	public String authUserDetails(@RequestBody LoginDetails loginDetails) {

		Authentication authentication;

		try {

			authentication = authenticationManager.authenticate(

					new UsernamePasswordAuthenticationToken(
							loginDetails.getUsername(), 
							loginDetails.getPassword())

			);

		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "Not validate";
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		String jwtToken = utlis.genrateJwtToken(userDetails);
		
		return jwtToken;
	}

}
