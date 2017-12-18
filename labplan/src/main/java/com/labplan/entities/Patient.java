package com.labplan.entities;

import com.labplan.entities.generic.Entity;

/**
 * Represents a patient/client registered in a medical laboratory. Each
 * {@link Patient} can have multiple {@link LabList}s.
 * 
 * @author Elian Doran
 *
 */
public class Patient extends Entity<Integer> {
	private String firstName;
	private String lastName;
	private Integer age;
	private Integer height;
	private Integer weight;

	/**
	 * Creates a new instance of a {@link Patient}.
	 */
	public Patient() {

	}

	/**
	 * Creates a new instance of a {@link Patient} with the given information.
	 * 
	 * @param id
	 *            The numerical ID of the {@link Patient}, also known as an entity
	 *            key.
	 * @param firstName
	 *            The first name of the {@link Patient}.
	 * @param lastName
	 *            The last name of the {@link Patient}.
	 * @param age
	 *            The age of the {@link Patient}, expressed in <em>years</em>.
	 * @param height
	 *            The height of the {@link Patient}, expressed in
	 *            <em>centimeters</em>.
	 * @param weight
	 *            The weight of the {@link Patient}, expressed in
	 *            <em>kilograms</em>.
	 */
	public Patient(Integer id, String firstName, String lastName, Integer age, Integer height, Integer weight) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.height = height;
		this.weight = weight;
	}

	/**
	 * Creates a new instance of a {@link Patient} with the given information.
	 * 
	 * @param firstName
	 *            The first name of the {@link Patient}.
	 * @param lastName
	 *            The last name of the {@link Patient}.
	 * @param age
	 *            The age of the {@link Patient}, expressed in <em>years</em>.
	 * @param height
	 *            The height of the {@link Patient}, expressed in
	 *            <em>centimeters</em>.
	 * @param weight
	 *            The weight of the {@link Patient}, expressed in
	 *            <em>kilograms</em>.
	 */
	public Patient(String firstName, String lastName, Integer age, Integer height, Integer weight) {
		this(null, firstName, lastName, age, height, weight);
	}

	/**
	 * Gets the first name of this {@link Patient}.
	 * 
	 * @return The first name of this {@link Patient}.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of this {@link Patient}.
	 * 
	 * @param firstName
	 *            The first name of this {@link Patient}.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of this {@link Patient}.
	 * 
	 * @return The last name of this {@link Patient}.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of this {@link Patient}.
	 * 
	 * @param lastName
	 *            The last name of this {@link Patient}.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the age of this {@link Patient}, expressed in <em>years</em>.
	 * 
	 * @return The age of this {@link Patient}.
	 */
	public Integer getAge() {
		return this.age;
	}

	/**
	 * Sets the age of this {@link Patient}, expressed in <em>years</em>.
	 * 
	 * @param age
	 *            The age of this {@link Patient}.
	 */
	public void setAge(Integer age) {
		this.age = age;
	}

	/**
	 * Gets the height of this {@link Patient}, expressed in <em>centimeters</em>.
	 * 
	 * @return The height of this {@link Patient}.
	 */
	public Integer getHeight() {
		return this.height;
	}

	/**
	 * Sets the height of this {@link Patient}, expressed in <em>centimeters</em>.
	 * 
	 * @param height
	 *            The height of this {@link Patient}.
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * Gets the weight of this {@link Patient}, expressed in <em>kilograms</em>.
	 * 
	 * @return The weight of this {@link Patient}.
	 */
	public Integer getWeight() {
		return this.weight;
	}

	/**
	 * Sets the weight of this {@link Patient}, expressed in <em>kilograms</em>.
	 * 
	 * @param weight
	 *            The weight of this {@link Patient}
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
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

	@Override
	public String toString() {
		return String.format("<Patient ID=%s, FirstName=%s, LastName=%s, Age=%d, Weight=%d, Height=%d>", id, firstName,
				lastName, age, weight, height);
	}
}
