package com.labplan.tests;

import static org.junit.Assert.*;
import java.sql.*;
import org.junit.*;

import com.labplan.persistence.*;

public class DatabaseConnectionFactoryTests {
	@Test
	public void testConnection() throws Exception {
		Connection conn = DatabaseConnectionFactory.getConnection();
		assertNotNull("SQL connection failed as it returned null.", conn);
	}
}
