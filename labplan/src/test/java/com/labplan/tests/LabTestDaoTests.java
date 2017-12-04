package com.labplan.tests;

import static org.junit.Assert.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.LabTest;
import com.labplan.persistence.sql.LabTestDao;

public class LabTestDaoTests {
	private static LabTestDao labTestDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		labTestDao = new LabTestDao();
		
		System.setProperty("java.util.logging.config.file", ClassLoader.getSystemResource("logging.properties").getPath());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testInsertion() {
		LabTest dummyTest = new LabTest("Blood test", "A panel of blood tests", 1.0f, 2.5f);
		LabTest sameTest;
		
		Integer testId = labTestDao.create(dummyTest);
		assertNotNull("SQL insertion for single entity failed.", testId);
		dummyTest.setId(testId);
		
		sameTest = labTestDao.read(dummyTest.getId());
		assertNotNull("SQL retrieval for single entity failed.", sameTest);
		assertEquals("Dummy entity and retrieved entity are not identical.", dummyTest, sameTest);
		assertTrue("SQL deletion for single entity failed.", labTestDao.delete(dummyTest));
	}
}
