package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.LoginDetails;
import com.demo.dto.LoginResponse;
import com.demo.entity.CustomUser;
import com.demo.repo.UserRepo;
import com.demo.security.JwtUtils;

@RestController
public class UserController {
	
	@Autowired
	private UserRepo urepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/save/user")
	public CustomUser saveAlluser(@RequestBody CustomUser request){
		
		CustomUser user = new CustomUser();
		
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		user.setStatus(request.getStatus());
		
		return urepo.save(user);
		
	}
	
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authUserDetails(@RequestBody LoginDetails loginDetails) {

		try {

			CustomUser user = urepo.findByEmail(loginDetails.getEmail());
			
			if( user == null ) {
				
				return ResponseEntity.status(401).build();
			}
			
			if(!passwordEncoder.matches(loginDetails.getPassword(), user.getPassword())) {
				
				return  ResponseEntity.status(401).build();
			}
			else {
				
				String jwt = jwtUtils.genrateJwtToken(user.getEmail(),user.getRole());
				
				return ResponseEntity.ok(new LoginResponse(jwt));
			}

		} catch (Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity.status(401).build();
		}
		
		
	}
}
