package com.labplan.persistence.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.labplan.entities.LabTest;
import com.labplan.persistence.DatabaseConnectionFactory;

import static com.labplan.persistence.DatabaseConnectionFactory.MSG_CONNECTION_FAILED;

public class LabTestDao implements com.labplan.persistence.generic.GenericLabTestDao {
	private static final Logger LOGGER = Logger.getGlobal();

	@Override
	public LabTest read(Integer key) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_tests` WHERE `test_id`=?";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, key);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return parseLabTest(result);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
		}

		return null;
	}
	
	@Override
	public LabTest read(String name) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_tests` WHERE `name`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, name);
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				return parseLabTest(result);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
		}
		
		return null;
	}

	@Override
	public Set<LabTest> readAll() {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_tests`";
		Set<LabTest> labTests = new HashSet<LabTest>();

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				labTests.add(parseLabTest(result));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}

		return labTests;
	}

	@Override
	public Integer create(LabTest entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "INSERT INTO `lab_tests` " + "(`name`, `description`, `value_min`, `value_max`) "
				+ "VALUES (?, ?, ?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys;

			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getDescription());
			stmt.setFloat(3, entity.getValueMin());
			stmt.setFloat(4, entity.getValueMax());

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
	public boolean update(LabTest entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "UPDATE `lab_tests` SET " + "`name`=?, " + "`description`=?, " + "`value_min`=?, "
				+ "`value_max`=?" + "WHERE `test_id`=?";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);

			stmt.setString(1, entity.getName());
			stmt.setString(2, entity.getDescription());
			stmt.setFloat(3, entity.getValueMin());
			stmt.setFloat(4, entity.getValueMax());
			stmt.setInt(5, entity.getId());

			stmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return false;
		}

		return true;
	}

	@Override
	public boolean delete(LabTest entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "DELETE FROM `lab_tests` WHERE `test_id`=?";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);

			stmt.setInt(1, entity.getId());
			stmt.execute();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return false;
		}

		return true;
	}

	private LabTest parseLabTest(ResultSet result) throws SQLException {
		LabTest labTest = new LabTest();

		labTest.setId(result.getInt("test_id"));
		labTest.setName(result.getString("name"));
		labTest.setDescription(result.getString("description"));
		labTest.setValueMin(result.getFloat("value_min"));
		labTest.setValueMax(result.getFloat("value_max"));

		return labTest;
	}
}
