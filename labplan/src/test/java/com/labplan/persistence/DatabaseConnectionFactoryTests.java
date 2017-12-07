package com.labplan.persistence;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.persistence.sql.PatientDao;

public class DatabaseConnectionFactoryTests {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionFactory.setProfile("test");
	}

	@Test
	public void testConnection() throws Exception {
		Connection conn = DatabaseConnectionFactory.getConnection();
		assertNotNull("SQL connection failed as it returned null.", conn);
	}
}
