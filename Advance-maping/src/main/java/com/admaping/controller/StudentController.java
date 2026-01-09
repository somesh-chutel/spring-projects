package com.admaping.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admaping.entity.Laptop;
import com.admaping.entity.Student;
import com.admaping.entity.repo.LaptopRepo;
import com.admaping.entity.repo.StudentRepo;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentRepo srepo;
	
	@Autowired
	private LaptopRepo laprepo;
	
	@PutMapping("/stu/{stuid}/lap/{lapid}")
	public String addUniqueData(@PathVariable("stuid") int stuid, @PathVariable("lapid") int lapid) {
		
		Student student = srepo.findById(stuid).get();
		
		Laptop laptop = laprepo.findById(lapid).get();
		
		Set<Laptop> loptops = null;
		
		loptops = student.getLaptop();
		
		loptops.add(laptop);
		
		srepo.save(student);
		
		return "Refrence table updated successfully";
	}
	
	
	@PostMapping("/saveall")
	public List<Student> addStudent(@RequestBody List<Student> student) {
			
		return srepo.saveAll(student);
	}
	
	@GetMapping("/getall")
	public List<Student> getAllStudent(){
		
		return srepo.findAll();
	}

}
