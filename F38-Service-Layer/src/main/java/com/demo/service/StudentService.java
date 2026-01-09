package com.demo.service;

import java.util.List;

import com.demo.entity.Student;

public interface StudentService {

		public List<Student> getAll();

		public Student saveMyStudent(Student student);

		public String updateStudent(int stuid, Student student);
}
