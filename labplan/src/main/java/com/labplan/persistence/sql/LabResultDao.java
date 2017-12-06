package com.labplan.persistence.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.labplan.entities.CompositeKeyPair;
import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.persistence.DatabaseConnectionFactory;

public class LabResultDao implements com.labplan.persistence.generic.LabResultDao {
	private static final Logger LOGGER = Logger.getGlobal();
	
	@Override
	public LabResult read(CompositeKeyPair<LabTest, LabList> key) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_results` WHERE `test_id`=? AND `list_id`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, key.getFirstKey().getId());
			stmt.setInt(2, key.getSecondKey().getId());
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				return parseResult(result);
			}
			
			return null;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "SQL connection failed.", e);
			return null;
		}
	}

	@Override
	public Set<LabResult> readAll() {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_results`";
		Set<LabResult> results = new HashSet<LabResult>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				results.add(parseResult(result));
			}
			
			return results;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "SQL connection failed.", e);
			return null;
		}
	}

	@Override
	public CompositeKeyPair<LabTest, LabList> create(LabResult entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "INSERT INTO `lab_results` "
				+ "(`test_id`, `list_id`, `value`) "
				+ "VALUES (?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, entity.getId().getFirstKey().getId());
			stmt.setInt(2, entity.getId().getSecondKey().getId());
			stmt.setFloat(3,  entity.getValue());
			
			stmt.execute();

			return entity.getId();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "SQL connection failed.", e);
			return null;
		}
	}

	@Override
	public boolean update(LabResult entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "UPDATE `lab_results` SET"
				+ "`value`=? "
				+ "WHERE `test_id`=? AND `list_id`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setFloat(1, entity.getValue());
			stmt.setInt(2, entity.getId().getFirstKey().getId());
			stmt.setInt(3, entity.getId().getSecondKey().getId());
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "SQL connection failed.", e);
			return false;
		}
	}

	@Override
	public boolean delete(LabResult entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "DELETE FROM `lab_results` WHERE `test_id`=? AND `list_id`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, entity.getId().getFirstKey().getId());
			stmt.setInt(2, entity.getId().getSecondKey().getId());
			stmt.execute();
			
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "SQL connection failed.", e);
			return false;
		}
	}
	
	private LabResult parseResult(ResultSet result) throws SQLException {
		LabResult labResult = new LabResult();
		
		LabTest mockTest = new LabTest();
		LabList mockList = new LabList();
		
		mockTest.setId(result.getInt("test_id"));
		mockList.setId(result.getInt("list_id"));
		
		labResult.setId(new CompositeKeyPair<LabTest, LabList>(mockTest, mockList));
		labResult.setValue(result.getFloat("value"));
		
		return labResult;
	}
}
