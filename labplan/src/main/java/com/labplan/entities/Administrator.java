package com.labplan.entities;

import com.labplan.entities.generic.Entity;

/**
 * Represents an user which is allowed to access and administer the backend.
 * 
 * @author Elian Doran
 *
 */
public class Administrator extends Entity<Integer> {
	private String firstName;
	private String lastName;
	private String userName;
	private String password;

	/**
	 * Creates a new instance of a {@link Administrator}.
	 */
	public Administrator() {

	}

	/**
	 * Creates a new instance of a {@link Administrator} with the given information.
	 * 
	 * @param id
	 *            The numerical ID of the {@link Administrator}, also known as an
	 *            entity key.
	 * @param firstName
	 *            The first name of the {@link Administrator}.
	 * @param lastName
	 *            The last name of the {@link Administrator}.
	 * @param userName
	 *            The user name of the {@link Administrator}.
	 * @param password
	 *            The password of the {@link Administrator}. The password must be hashed in a hexadecimal SHA-256 (64 characters long).
	 */
	public Administrator(Integer id, String firstName, String lastName, String userName, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}
	
	/**
	 * Creates a new instance of a {@link Administrator} with the given information.
	 * 
	 * @param id
	 *            The numerical ID of the {@link Administrator}, also known as an
	 *            entity key.
	 * @param firstName
	 *            The first name of the {@link Administrator}.
	 * @param lastName
	 *            The last name of the {@link Administrator}.
	 * @param userName
	 *            The user name of the {@link Administrator}.
	 * @param password
	 *            The password of the {@link Administrator}. The password must be hashed in a hexadecimal SHA-256 (64 characters long).
	 */
	public Administrator(String firstName, String lastName, String userName, String password) {
		this(null, firstName, lastName, userName, password);
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
