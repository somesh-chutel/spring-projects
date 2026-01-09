package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entity.Student;
import com.demo.entity.repo.StudentRepo;

@Service
public class StudentServiceImp implements StudentService {
	
	@Autowired
	private StudentRepo srepo;
	
	public List<Student> getAll(){
		
		return srepo.findAll();
		
	}


	public Student saveMyStudent(Student student) {
		
		return srepo.save(student);
	}



	public String updateStudent(int stuid, Student student) {
		
		Student myStudent = srepo.findById(stuid).get(); 
		
		myStudent.setName(student.getName());
		myStudent.setGender(student.getGender());
		myStudent.setAge(student.getAge());
		
		srepo.save(myStudent);
		
		return "Student Data Updated successfully";
	}
	
	public String deleteStudent(int stuid) {
		
		srepo.deleteById(stuid);
		
		return null;
	}

}
