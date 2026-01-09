package com.demo.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stuId;
	private String stuName;
	private int stuAge;
	private String stuStatus;
	
	@OneToMany(mappedBy = "student")
	private List<Subject> subject;
}
