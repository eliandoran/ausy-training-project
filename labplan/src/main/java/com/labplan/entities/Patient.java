package com.labplan.entities;

public class Patient {
	private Integer id;
	private String firstName;
	private String lastName;
	private Integer age;
	private Integer height;
	private Integer weight;
	
	
	public Patient() {
		
	}
	
	public Patient(Integer id, String firstName, String lastName, Integer age, Integer height, Integer weight) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	
	public Patient(String firstName, String lastName, Integer age, Integer height, Integer weight) {
		this(null, firstName, lastName, age, height, weight);
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Integer getAge() {
		return this.age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Integer getHeight() {
		return this.height;
	}
	
	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public Integer getWeight() {
		return this.weight;
	}
	
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
}
