package xyz.doran.elian.labplan.persistence;

import java.io.*;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;

public class DatabaseConnectionFactory {
	private static Properties properties;
	
	private static String configFilePath;

	public static String getConfigFilePath() {
		return configFilePath;
	}

	public static void setConfigFilePath(String path) {
		configFilePath = path;
	}
	
	public static Connection getConnection() throws IOException, SQLException {
		if (properties == null)
			load();
		
		DriverManager.registerDriver(new Driver());
		
		return DriverManager.getConnection(
				properties.getProperty("address"),
				properties.getProperty("username"),
				properties.getProperty("password"));
	}
	
	public static void load() throws IOException {
		FileInputStream in = null;
		
		try {
			in = new FileInputStream(configFilePath);
			properties = new Properties();
			properties.load(in);
			
			if (!properties.containsKey("address") ||
				properties.getProperty("address").length() == 0)
				throw new RuntimeException("Database address not found in configuration file.");
			
			if (!properties.containsKey("username") ||
				properties.getProperty("username").length() == 0)
				throw new RuntimeException("User name not found in configuration file.");
			
			if (!properties.containsKey("password"))
				throw new RuntimeException("Password not found in configuration file.");
		} finally {
			in.close();
		}
	}
	
	public static void save(String address, String userName, String password)
			throws IOException {
		FileOutputStream out = null;
		
		try {
			out = new FileOutputStream(configFilePath);
			Properties props = new Properties();
			
			props.setProperty("address", address);
			props.setProperty("username", userName);
			props.setProperty("password", password);
			
			props.save(out, "LabPlan Database Connection Parameters");
		} finally {
			out.close();
		}
	}
}
