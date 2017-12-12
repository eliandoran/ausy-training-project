package com.labplan.persistence.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.labplan.entities.Patient;
import com.labplan.persistence.DatabaseConnectionFactory;
import com.labplan.persistence.generic.PatientDao;
import com.mysql.cj.api.jdbc.Statement;

import static com.labplan.persistence.DatabaseConnectionFactory.MSG_CONNECTION_FAILED;

/**
 * A MySQL-compatible implementation of the {@link PatientDao}.
 * 
 * @author Elian Doran
 * @see Patient
 * @see PatientDao
 */
public class SqlPatientDao implements PatientDao {
	private static final Logger LOGGER = Logger.getGlobal();

	@Override
	public Patient read(Integer id) {
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
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
		}

		return null;
	}

	@Override
	public Patient read(String firstName, String lastName) {
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
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
		}

		return null;
	}
	
	@Override
	public List<Patient> read(Integer limit, Integer offset) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `patients` LIMIT ? OFFSET ?";
		List<Patient> patients = new LinkedList<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);
			
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				patients.add(parsePatient(result));
			}
			
			return patients;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}
	}

	@Override
	public Set<Patient> readAll() {
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
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}

		return patients;
	}

	@Override
	public Integer create(Patient patient) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "INSERT INTO `patients` " + "(`first_name`, `last_name`, `age`, `height`, `weight`)"
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
				return generatedKeys.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
		}

		return null;
	}

	@Override
	public boolean update(Patient patient) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "UPDATE `patients` SET " + "`first_name`=?, " + "`last_name`=?, " + "`age`=?, " + "`height`=?, "
				+ "`weight`=? " + "WHERE `patient_id`=?";

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
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return false;
		}

		return true;
	}

	@Override
	public boolean delete(Patient patient) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "DELETE FROM `patients` WHERE `patient_id`=?";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);

			stmt.setInt(1, patient.getId());
			stmt.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
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
	
	@Override
	public Integer getPatientsCount() {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT COUNT(*) FROM `patients`";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				return result.getInt(1);
			}
			
			return null;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}
	}

	@Override
	public boolean truncate() {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "DELETE FROM `patients`";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return false;
		}
	}
}
