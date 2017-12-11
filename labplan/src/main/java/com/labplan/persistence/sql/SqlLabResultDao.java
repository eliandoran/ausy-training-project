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

import static com.labplan.persistence.DatabaseConnectionFactory.MSG_CONNECTION_FAILED;

public class SqlLabResultDao implements com.labplan.persistence.generic.LabResultDao {
	private static final Logger LOGGER = Logger.getGlobal();
	
	private LabList list;
	
	public SqlLabResultDao(LabList parentEntity) {
		list = parentEntity;
	}
	
	@Override
	public LabResult read(LazyLoadedEntity<Integer, LabTest> key) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_results` WHERE `list_id`=? AND `test_id`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, list.getId());
			stmt.setInt(2, key.getKey());
			ResultSet result = stmt.executeQuery();
			
			if (result.next()) {
				return parseResult(result);
			}

			return null;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}
	}

	@Override
	public Set<LabResult> readAll() {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_results` WHERE `list_id`=?";
		Set<LabResult> results = new HashSet<>();
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, list.getId());
			ResultSet result = stmt.executeQuery();
			
			while (result.next()) {
				results.add(parseResult(result));
			}
			
			return results;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}
	}

	@Override
	public LazyLoadedEntity<Integer, LabTest> create(LabResult entity) {
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
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
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
			stmt.setInt(2, entity.getId().getKey());
			stmt.setInt(3, list.getId());
			
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return false;
		}
	}

	@Override
	public boolean delete(LabResult entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "DELETE FROM `lab_results` WHERE `test_id`=? AND `list_id`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, entity.getId().getKey());
			stmt.setInt(2, list.getId());
			stmt.execute();
			
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return false;
		}
	}
	
	@Override
	public boolean updateOrCreate(LabResult entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "INSERT INTO `lab_results` "
				+ "(`test_id`, `list_id`, `value`) "
				+ "VALUES (?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE "
				+ "`value`=?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, entity.getId().getKey());
			stmt.setInt(2, list.getId());
			stmt.setFloat(3, entity.getValue());
			stmt.setFloat(4, entity.getValue());
			stmt.execute();
			
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return false;
		}
	}
	
	private LabResult parseResult(ResultSet result) throws SQLException {
		LabResult labResult = new LabResult();
		
		LazyLoadedEntity<Integer, LabTest> lazyTest = new LazyLoadedEntity<Integer, LabTest>();
		lazyTest.setKey(result.getInt("test_id"));
		
		lazyTest.setDao(new SqlLabTestDao());
		
		labResult.setId(lazyTest);
		labResult.setValue(result.getFloat("value"));
		
		return labResult;
	}

	@Override
	public LabList getParentEntity() {
		return list;
	}
}
