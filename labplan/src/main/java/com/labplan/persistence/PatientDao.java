package com.labplan.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.labplan.entities.Patient;
import com.labplan.persistence.exceptions.ConnectionFailedException;
import com.mysql.cj.api.jdbc.Statement;

public class PatientDao implements com.labplan.persistence.generic.IPatientDao {

	public Patient getPatientById(int id) throws ConnectionFailedException {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `patients` WHERE `patient_id`=?";
		
		try {			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, id);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				return parsePatient(result);
			}
		} catch (SQLException e) {
			return null;
		}
		
		return null;
	}
	
	public Patient getPatientByName(String firstName, String lastName) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `patients` WHERE `first_name`=? AND `last_name`=?";
		
		try {			
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, firstName);
			stmt.setString(2, lastName);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				return parsePatient(result);
			}
		} catch (SQLException e) {
			return null;
		}
		
		return null;
	}

	public Set<Patient> getAllPatients() {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `patients`";
		Set<Patient> patients = new HashSet<Patient>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				patients.add(parsePatient(result));
			}
		} catch (SQLException e) {
			return null;
		}
		
		return patients;
	}

	public boolean insertPatient(Patient patient) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "INSERT INTO `patients` "
				+ "(`first_name`, `last_name`, `age`, `height`, `weight`)"
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys;
			
			stmt.setString(1, patient.getFirstName());
			stmt.setString(2, patient.getLastName());
			stmt.setInt(3, patient.getAge());
			stmt.setInt(4, patient.getHeight());
			stmt.setInt(5, patient.getWeight());
			
			stmt.execute();
			generatedKeys = stmt.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				patient.setId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}

	public boolean updatePatient(Patient patient) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "UPDATE `patients` SET "
				+ "`first_name`=?, "
				+ "`last_name`=?, "
				+ "`age`=?, "
				+ "`height`=?, "
				+ "`weight`=? "
				+ "WHERE `patient_id`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setString(1, patient.getFirstName());
			stmt.setString(2, patient.getLastName());
			stmt.setInt(3, patient.getAge());
			stmt.setInt(4, patient.getHeight());
			stmt.setInt(5, patient.getWeight());
			stmt.setInt(6, patient.getId());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}

	public boolean deletePatient(Patient patient) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "DELETE FROM `patients` WHERE `patient_id`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setInt(1, patient.getId());
			stmt.execute();
		} catch (SQLException e) {
			return false;
		}
		
		return true;
	}

	private Patient parsePatient(ResultSet result) throws SQLException {
		Patient patient = new Patient();
		
		patient.setId(result.getInt("patient_id"));
		patient.setFirstName(result.getString("first_name"));
		patient.setLastName(result.getString("last_name"));
		patient.setAge(result.getInt("age"));
		patient.setHeight(result.getInt("height"));
		patient.setWeight(result.getInt("weight"));
		
		return patient;
	}
}
