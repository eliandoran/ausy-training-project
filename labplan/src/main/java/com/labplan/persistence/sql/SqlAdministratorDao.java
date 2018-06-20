package com.labplan.persistence.sql;

import static com.labplan.persistence.DatabaseConnectionFactory.MSG_CONNECTION_FAILED;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.labplan.entities.Administrator;
import com.labplan.persistence.DatabaseConnectionFactory;
import com.labplan.persistence.generic.AdministratorDao;

/**
 * A MySQL-compatible implementation of the {@link AdministratorDao}.
 * 
 * @author Elian Doran
 * @see Administrator
 * @see AdministratorDao
 *
 */
public class SqlAdministratorDao implements AdministratorDao {
	private static final Logger LOGGER = Logger.getGlobal();

	@Override
	public Administrator read(Integer key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Administrator> readAll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer create(Administrator entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean update(Administrator entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean delete(Administrator entity) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean validateAuthentication(String userName, String password) {
		Connection conn = DatabaseConnectionFactory.getConnection();
		String query = "SELECT * FROM `administrators` WHERE `user_name`=? AND `password`=?";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, userName);
			stmt.setString(2, password);
			ResultSet result = stmt.executeQuery();

			return result.next();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, MSG_CONNECTION_FAILED, e);
		}
		
		return false;
	}

}
