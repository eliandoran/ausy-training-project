package com.labplan.persistence;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;

import org.junit.Test;

public class DatabaseConnectionFactoryTests {
	@Test
	public void testConnection() throws Exception {
		Connection conn = DatabaseConnectionFactory.getConnection();
		assertNotNull("SQL connection failed as it returned null.", conn);
	}
}
