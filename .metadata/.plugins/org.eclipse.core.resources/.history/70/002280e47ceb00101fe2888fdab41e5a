package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Student;
import com.demo.service.StudentService;


@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private StudentService sservice;
	
	@GetMapping("/getall")
	public List<Student> findAllStudent(){
		
		return sservice.getAll();
	}
	
	@PostMapping("/save")
	public String saveStudent(@RequestBody Student student) {
		
		sservice.saveMyStudent(student);
		
		return "Student data saved successfully";
	}
	
	@PutMapping("/update/{id}")
	public String updateStudent(@PathVariable("id") int stuid,@RequestBody Student student) {
		
		sservice.updateStudent(stuid,student);
		
		
		return "Student Data updated Successfully";
	}

}
