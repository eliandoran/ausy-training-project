package xyz.doran.elian.labplan.persistence;

import java.io.*;
import java.net.URL;
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
				properties.getProperty("address", ""),
				properties.getProperty("username", ""),
				properties.getProperty("password", ""));
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
	
	public static void save(String address, String userName, String password)
			throws IOException {
		FileOutputStream out = null;
		
		try {
			out = new FileOutputStream(configFilePath);
			Properties props = new Properties();
			
			props.setProperty("address", address);
			props.setProperty("username", userName);
			props.setProperty("password", password);
			
			props.store(out, "LabPlan Database Connection Parameters");
		} finally {
			out.close();
		}
	}
}
