package com.labplan.persistence.sql;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.generic.LazyLoadedEntity;

import static com.labplan.helpers.TestMessages.*;

public class LabResultDaoTests {
	private static LabList dummyLabList;
	private static LabTest dummyLabTest;
	
	private static LabResultDao dao;
	private static LabListDao listDao;
	private static LabTestDao testDao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Generate a random LabList.
		listDao = new LabListDao();
		
		LabListDaoTests listTests = new LabListDaoTests();
		dummyLabList = listTests.getRandomEntity();
		dummyLabList.setId(listDao.create(dummyLabList));
		assertNotNull(MSG_INSERTION_FAILED, dummyLabList.getId());
		
		// Generate a random LabTest.
		testDao = new LabTestDao();
		
		LabTestDaoTests testTests = new LabTestDaoTests();
		dummyLabTest = testTests.getRandomEntity();
		dummyLabTest.setId(testDao.create(dummyLabTest));
		assertNotNull(MSG_INSERTION_FAILED, dummyLabTest.getId());
		
		dao = new LabResultDao(dummyLabList);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		assertNotNull(MSG_DELETION_FAILED, listDao.delete(dummyLabList));
	}

	@Test
	public void testInsertion() {
		// CREATE a generated LabResult.
		LabResult dummyResult = getRandomEntity();
		dummyResult.setId(dao.create(dummyResult));
		assertNotNull(MSG_INSERTION_FAILED, dummyResult.getId());
		
		// READ it back and compare it.
		Set<LabResult> results = dao.readAll();
		assertTrue(MSG_RETRIEVAL_FAILED, !results.isEmpty());
		
		boolean found = false;
		
		for (LabResult result : results) {
			if (result.equals(dummyResult)) {
				found = true;
				break;
			}
		}
		
		assertTrue(MSG_ENTITY_NOT_FOUND, found);
	}
	
	public LabResult getRandomEntity() {
		LabResult dummyResult = new LabResult();
		
		dummyResult.setId(new LazyLoadedEntity<Integer, LabTest>(dummyLabTest));
		
		// Set a random value
		Random random = new Random();
		dummyResult.setValue(random.nextFloat() * 10);
		
		return dummyResult;
	}
}
