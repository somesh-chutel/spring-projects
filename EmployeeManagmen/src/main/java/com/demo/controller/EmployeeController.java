package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.LoginDetails;
import com.demo.dto.LoginResponse;
import com.demo.entity.Employee;
import com.demo.entity.repo.EmployeeRepo;
import com.demo.security.JwtUtils;

@RestController
public class EmployeeController {
	
	@Autowired 
	private EmployeeRepo empRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private JwtUtils jwtUtils;
	
	@PostMapping("/save")
	public Employee saveEmployee(@RequestBody Employee employee) {
			
		Employee myEmp = new Employee();
		
		myEmp.setEmail(employee.getEmail());
		myEmp.setName(employee.getName());
		myEmp.setPassword(passwordEncoder.encode(employee.getPassword()));
		myEmp.setRole(employee.getRole());
		
		return empRepo.save(myEmp);
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> authUserDetails(@RequestBody LoginDetails loginDetails) {

		try {
				
				Employee myEmp = empRepo.findByEmail(loginDetails.getEmail());
				
				if( myEmp != null && passwordEncoder.matches(loginDetails.getPassword(), myEmp.getPassword())) {
					
						 String jwt = jwtUtils.genrateJwtToken(myEmp.getEmail(),myEmp.getRole());
						 
						 return ResponseEntity.ok(LoginResponse(jwt));
				}
				
				return ResponseEntity.status(401).build();

			

		} catch (Exception e) {
			
			e.printStackTrace();
			
			return ResponseEntity.status(401).build();
		}
		
	}
	
	
}
