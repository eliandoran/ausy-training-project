package xyz.doran.elian.labplan.junit;

import static org.junit.Assert.*;
import java.sql.*;
import org.junit.*;

import xyz.doran.elian.labplan.persistence.*;

public class DatabaseConnectionFactoryTests {
	@Test
	public void testConnection() throws Exception {
		Connection conn = DatabaseConnectionFactory.getConnection();
		assertNotNull("SQL connection failed as it returned null.", conn);
	}
}
