package com.labplan.persistence.sql;

import static com.labplan.persistence.DatabaseConnectionFactory.MSG_CONNECTION_FAILED;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.Patient;
import com.labplan.entities.generic.LazyLoadedEntity;
import com.labplan.persistence.DatabaseConnectionFactory;
import com.labplan.persistence.generic.LabListDao;

/**
 * A MySQL-compatible implementation of the {@link LabListDao}.
 * 
 * @author Elian Doran
 * @see LabListDao
 * @see LabList
 */
public class SqlLabListDao implements LabListDao {
	private static final Logger LOGGER = Logger.getGlobal();

	@Override
	public LabList read(Integer key) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_lists` WHERE `list_id`=?";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, key);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return parseLabList(result);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
		}

		return null;
	}

	@Override
	public LabList read(Integer key, boolean loadResults) {
		LabList list = read(key);

		if (loadResults) {
			SqlLabResultDao resultDao = new SqlLabResultDao(list);
			List<LabResult> results = new LinkedList<>();
			results.addAll(resultDao.readAll());
			list.setResults(results);
		}

		return list;
	}

	@Override
	public List<LabList> read(Integer limit, Integer offset) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_lists` LIMIT ? OFFSET ?";
		List<LabList> lists = new LinkedList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, limit);
			stmt.setInt(2, offset);

			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				lists.add(parseLabList(result));
			}

			return lists;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}
	}

	@Override
	public List<LabList> read(Patient patient, Integer limit, Integer offset) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_lists` WHERE `patient_id`=? LIMIT ? OFFSET ?";
		List<LabList> lists = new LinkedList<>();

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, patient.getId());
			stmt.setInt(2, limit);
			stmt.setInt(3, offset);

			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				lists.add(parseLabList(result));
			}

			return lists;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}
	}

	@Override
	public Set<LabList> readAll() {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_lists`";
		Set<LabList> labLists = new HashSet<LabList>();

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				labLists.add(parseLabList(result));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}

		return labLists;
	}

	@Override
	public Set<LabList> readAllByPatient(Patient patient) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `lab_lists` WHERE `patient_id`=?";
		Set<LabList> labLists = new HashSet<LabList>();

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, patient.getId());
			ResultSet result = stmt.executeQuery();

			while (result.next()) {
				labLists.add(parseLabList(result));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}

		return labLists;
	}

	@Override
	public Integer create(LabList entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "INSERT INTO `lab_lists` " + "(`patient_id`, `creation_date`) " + "VALUES (?, ?)";

		try {
			PreparedStatement stmt = conn.prepareStatement(query, java.sql.Statement.RETURN_GENERATED_KEYS);
			ResultSet generatedKeys;

			stmt.setInt(1, entity.getPatient().getKey());
			stmt.setTimestamp(2, parseDate(entity.getCreationDate()));

			stmt.execute();
			generatedKeys = stmt.getGeneratedKeys();

			if (generatedKeys.next()) {
				Integer key = generatedKeys.getInt(1);

				updateResults(key, entity);

				return key;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
		}

		return null;
	}

	@Override
	public boolean update(LabList entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "UPDATE `lab_lists` SET " + "`patient_id`=?, " + "`creation_date`=? " + "WHERE `list_id`=?";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);

			stmt.setInt(1, entity.getPatient().getKey());
			stmt.setTimestamp(2, parseDate(entity.getCreationDate()));
			stmt.setInt(3, entity.getId());

			stmt.executeUpdate();

			updateResults(entity.getId(), entity);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return false;
		}

		return true;
	}

	@Override
	public boolean delete(LabList entity) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "DELETE FROM `lab_lists` WHERE `list_id`=?";

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

	@Override
	public Integer getCount() {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT COUNT(*) FROM `lab_lists`";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return result.getInt(1);
			}

			return 0;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}
	}

	@Override
	public Integer getCount(Patient patient) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT COUNT(*) FROM `lab_lists` WHERE `patient_id`=?";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1, patient.getId());

			ResultSet result = stmt.executeQuery();

			if (result.next()) {
				return result.getInt(1);
			}

			return 0;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
			return null;
		}
	}

	private LabList parseLabList(ResultSet result) throws SQLException {
		LabList list = new LabList();

		// Parse Patient by means of Patient ID.
		Integer patientId = result.getInt("patient_id");
		LazyLoadedEntity<Integer, Patient> lazyPatient = new LazyLoadedEntity<>();
		lazyPatient.setKey(patientId);
		lazyPatient.setDao(new SqlPatientDao());

		list.setPatient(lazyPatient);

		// Parse the rest of the information.
		list.setId(result.getInt("list_id"));
		list.setCreationDate(parseDate(result.getTimestamp("creation_date")));

		return list;
	}

	private void updateResults(Integer key, LabList _entity) {
		if (_entity.getResults() == null)
			return;

		LabList entity = _entity.shallowCopy();
		entity.setId(key);

		SqlLabResultDao resultDao = new SqlLabResultDao(entity);
		resultDao.deleteAll();

		for (LabResult result : entity.getResults()) {
			resultDao.create(result);
		}
	}

	private Timestamp parseDate(java.util.Date creationDate) {
		return new Timestamp(creationDate.getTime());
	}

	private java.util.Date parseDate(Timestamp creationDate) {
		return new java.util.Date(creationDate.getTime());
	}
}
