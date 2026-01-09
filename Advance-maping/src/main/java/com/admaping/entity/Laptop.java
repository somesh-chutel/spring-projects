package com.admaping.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Laptop {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int lapId;
	private String brand;
	
	@ManyToMany(mappedBy = "laptop",cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "laptop")
	private List<Student> student;
	

}
