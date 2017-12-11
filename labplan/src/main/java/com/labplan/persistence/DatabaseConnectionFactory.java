package com.labplan.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.labplan.persistence.exceptions.ConnectionFailedException;
import com.mysql.cj.jdbc.Driver;

/**
 * Generates a {@link Connection} by obtaining authentication data (including connection URL) from {@code db.properties} resource file.
 * The {@code db.properties} can contain multiple sets of authentication data, called <i>profiles</i>.
 * 
 * The properties are as follows:
 * <ul>
 * 	<li><i>profile</i>.address &ndash; A JDBC connection URL, which includes the host and the database.</li>
 *  <li><i>profile</i>.username &ndash; The user name used for connecting to the database.</li>
 * 	<li><i>profile</i>.password &ndash; The password used for connecting to the database.</li>
 * </ul>
 * 
 * Where <i>profile</i> defaults to "dev" and can be set to any value with {@code setProfile}.
 * @author Elian Doran
 * @see Properties
 *
 */
public class DatabaseConnectionFactory {
	private static Properties properties;

	private static String profile = "dev";
	
	public static final String MSG_CONNECTION_FAILED = "SQL connection failed.";
	
	/**
	 * Obtains a {@link Connection} by using a default {@link DriverManager} and using authentication data obtained from {@code db.properties}.
	 * The authentication data loaded are those belonging to the profile set with {@code setProfile()} or {@code "dev"} if not specified.
	 * @return The newly generated {@link Connection}.
	 * @throws ConnectionFailedException When the SQL connection failed due to invalid authentication credentials or database URL.
	 * @see DatabaseConnectionFactory
	 */
	public static Connection getConnection() throws ConnectionFailedException {
		try {
			if (properties == null)
				load();

			DriverManager.registerDriver(new Driver());

			String prefix = profile + ".";
			
			return DriverManager.getConnection(
					properties.getProperty(prefix + "address", ""),
					properties.getProperty(prefix + "username", ""),
					properties.getProperty(prefix + "password", ""));
		} catch (IOException | SQLException e) {
			ConnectionFailedException newException = new ConnectionFailedException();
			newException.addSuppressed(e);
			throw newException;
		}
	}

	private static void load() throws IOException {
		ClassLoader loader = DatabaseConnectionFactory.class.getClassLoader();
		URL resource = loader.getResource("db.properties");
		InputStream in = null;

		try {
			in = resource.openStream();

			properties = new Properties();
			properties.load(in);
		} finally {
			in.close();
		}
	}

	/**
	 * Obtains the name of the currently active <i>profile</i>.
	 * The <i>profile</i> represents a set of authentication details, which include user name, password & URL in order to obtain a database connection.
	 * The default profile is {@code "dev"}.
	 * @return
	 */
	public static String getProfile() {
		return profile;
	}

	/**
	 * Sets the authentication profile used by {@code getConnection()} to obtain a connection.
	 * The <i>profile</i> represents a set of authentication details, which include user name, password & URL in order to obtain a database connection.
	 * @param profile The name of the authentication profile to use as default.
	 */
	public static void setProfile(String profile) {
		DatabaseConnectionFactory.profile = profile;
	}
}
