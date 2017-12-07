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

public class DatabaseConnectionFactory {
	private static Properties properties;

	private static String profile = "dev";
	
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

	public static String getProfile() {
		return profile;
	}

	public static void setProfile(String profile) {
		DatabaseConnectionFactory.profile = profile;
	}
}
