package com.labplan.persistence.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.generic.LazyLoadedEntity;
import com.labplan.persistence.DatabaseConnectionFactory;

public class LabResultDao implements com.labplan.persistence.generic.GenericLabResultDao {
	private static final Logger LOGGER = Logger.getGlobal();
	
	@Override
	public Set<LabResult> read(LabList list) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_results` WHERE `list_id`=?";
		Set<LabResult> results = new HashSet<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, list.getId());
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				results.add(parseResult(list, result));
			}
			
			return results;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "SQL connection failed.", e);
			return null;
		}
	}

	@Override
	public LazyLoadedEntity<Integer, LabTest> create(LabList list, LabResult entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "INSERT INTO `lab_results` "
				+ "(`test_id`, `list_id`, `value`) "
				+ "VALUES (?, ?, ?)";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, entity.getId().getKey());
			stmt.setInt(2, list.getId());
			stmt.setFloat(3,  entity.getValue());
			
			stmt.execute();

			return entity.getId();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "SQL connection failed.", e);
			return null;
		}
	}

	@Override
	public boolean update(LabList list, LabResult entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "UPDATE `lab_results` SET"
				+ "`value`=? "
				+ "WHERE `test_id`=? AND `list_id`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			
			stmt.setFloat(1, entity.getValue());
			stmt.setInt(2, entity.getId().getKey());
			stmt.setInt(3, list.getId());
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "SQL connection failed.", e);
			return false;
		}
	}

	@Override
	public boolean delete(LabList list, LabResult entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "DELETE FROM `lab_results` WHERE `test_id`=? AND `list_id`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, entity.getId().getKey());
			stmt.setInt(2, list.getId());
			stmt.execute();
			
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "SQL connection failed.", e);
			return false;
		}
	}
	
	private LabResult parseResult(LabList list, ResultSet result) throws SQLException {
		LabResult labResult = new LabResult();
		
		LazyLoadedEntity<Integer, LabTest> lazyTest = new LazyLoadedEntity<Integer, LabTest>();
		lazyTest.setKey(result.getInt("test_id"));
		
		lazyTest.setDao(new LabTestDao());
		
		labResult.setId(new LazyLoadedEntity<Integer,LabTest>());
		labResult.setValue(result.getFloat("value"));
		
		return labResult;
	}
}
