package com.labplan.entities;

public class Patient extends Entity<Integer> {
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

	public boolean equals_no_id(Object obj) {
		if (!(obj instanceof Patient))
			return false;

		Patient patient = (Patient) obj;

		return (patient.firstName.equals(firstName) && patient.lastName.equals(lastName) && patient.age.equals(age)
				&& patient.weight.equals(weight) && patient.height.equals(height));
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Patient))
			return false;

		return hashCode() == obj.hashCode();
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + id.hashCode();
		hash = 31 * hash + firstName.hashCode();
		hash = 31 * hash + lastName.hashCode();
		hash = 31 * hash + age.hashCode();
		hash = 31 * hash + weight.hashCode();
		hash = 31 * hash + height.hashCode();
		return hash;
	}
}
