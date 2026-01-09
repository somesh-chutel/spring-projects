package com.admaping.entity;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Data
@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stuId;
	private String name;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="stu_lap",joinColumns = @JoinColumn(name="stuId"),inverseJoinColumns = @JoinColumn(name="lapId"))
	@JsonIgnoreProperties(value = "student")
	private Set<Laptop> laptop;
	
}
