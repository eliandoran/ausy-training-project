package com.labplan.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;
import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.LabTest;
import com.labplan.persistence.sql.LabTestDao;
import com.labplan.tests.helpers.CrudTester;

public class LabTestDaoTests extends CrudTester<Integer, LabTest, LabTestDao> {
	private static LabTestDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new LabTestDao();

		System.setProperty("java.util.logging.config.file",
				ClassLoader.getSystemResource("logging.properties").getPath());
	}
	
	@Test
	public void testGetByName() {
		LabTest dummyTest = getRandomEntity();
		Integer testId = dao.create(dummyTest);
		assertNotNull("SQL insertion for single LabTest failed.", testId);
		dummyTest.setId(testId);
		
		LabTest sameTest = dao.read(dummyTest.getName());
		assertEquals(dummyTest, sameTest);
		
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
	public LabTestDao getDao() {
		return dao;
	}
}
