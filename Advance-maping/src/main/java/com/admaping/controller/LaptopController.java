package com.admaping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admaping.entity.Laptop;
import com.admaping.entity.Student;
import com.admaping.entity.repo.LaptopRepo;
import com.admaping.entity.repo.StudentRepo;

@RestController
@RequestMapping("/laptop")
public class LaptopController {
	
	
	@Autowired
	private StudentRepo srepo;
	
	@Autowired
	private LaptopRepo laprepo;
	
	@GetMapping("/getall")
	public List<Laptop> saveLap() {
			
		return laprepo.findAll();
	}
	
	
	@PostMapping("/saveall")
	public List<Laptop> addStudent(@RequestBody List<Laptop> laptop) {
			
		return laprepo.saveAll(laptop);
	}
	
	@GetMapping("/lap/{id}")
	public Laptop getLap(@PathVariable("id") int lapId) {
			
		return laprepo.findById(lapId).get();
	}
}
