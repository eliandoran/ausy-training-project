package com.labplan.persistence;

import static org.junit.Assert.*;
import java.sql.*;
import org.junit.*;

public class DatabaseConnectionFactoryTests {
	@Test
	public void testConnection() throws Exception {
		Connection conn = DatabaseConnectionFactory.getConnection();
		assertNotNull("SQL connection failed as it returned null.", conn);
	}
}
