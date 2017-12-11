package com.labplan.persistence.sql;

import static com.labplan.helpers.TestMessages.MSG_INSERTION_FAILED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.LabTest;
import com.labplan.helpers.DaoTester;
import com.labplan.persistence.DatabaseConnectionFactory;

public class SqlLabTestDaoTests extends DaoTester<Integer, LabTest, SqlLabTestDao> {
	private static SqlLabTestDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionFactory.setProfile("test");
		dao = new SqlLabTestDao();

		System.setProperty("java.util.logging.config.file",
				ClassLoader.getSystemResource("logging.properties").getPath());
	}
	
	@Test
	public void testGetByName() {
		// CREATE a random lab test.
		LabTest dummyTest = getRandomEntity();
		dummyTest.setId(dao.create(dummyTest));
		assertNotNull(MSG_INSERTION_FAILED, dummyTest.getId());
		
		// READ the same lab test, but searching by its name. Then compare it with its counterpart.
		LabTest sameTest = dao.read(dummyTest.getName());
		assertEquals(dummyTest, sameTest);
		
		// DELETE the generated lab test.
		dao.delete(dummyTest);
	}
	
	@Test
	public void testInexistentGetByName() {
		assertNull(dao.read(UUID.randomUUID().toString()));
	}

	@Override
	public LabTest getRandomEntity() {
		return new LabTest(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1.0f, 2.5f);
	}

	@Override
	public SqlLabTestDao getDao() {
		return dao;
	}
}
