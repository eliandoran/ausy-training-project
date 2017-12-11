package com.labplan.persistence.sql;

import static com.labplan.helpers.TestMessages.MSG_DELETION_FAILED;
import static com.labplan.helpers.TestMessages.MSG_ENTITY_NOT_FOUND;
import static com.labplan.helpers.TestMessages.MSG_INSERTION_FAILED;
import static com.labplan.helpers.TestMessages.MSG_RETRIEVAL_FAILED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.generic.LazyLoadedEntity;
import com.labplan.helpers.DependentCrudTester;

public class LabResultDaoTests extends DependentCrudTester<LabList, LazyLoadedEntity<Integer, LabTest>, LabResult, LabResultDao> {
	private static LabList dummyLabList;
	private static LabTest dummyLabTest;
	
	private static LabResultDao dao;
	private static LabListDao listDao;
	private static LabTestDao testDao;
	
	public LabResultDaoTests() {
		
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		assertNotNull(MSG_DELETION_FAILED, listDao.delete(dummyLabList));
	}

	@Override
	public LabResult getRandomEntity() {
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
				
		LabResult dummyResult = new LabResult();
		
		dummyResult.setId(new LazyLoadedEntity<Integer, LabTest>(dummyLabTest));
		
		// Set a random value
		Random random = new Random();
		dummyResult.setValue(random.nextFloat() * 10);
		
		return dummyResult;
	}
	
	@Test
	public void testInsertion2() {
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
	
	@Test
	public void testUpdateOrCreate() {
		// CREATE a generated LabResult.
		LabResult dummyResult = getRandomEntity();
		dummyResult.setId(dao.create(dummyResult));
		assertNotNull(MSG_INSERTION_FAILED, dummyResult.getId());
		
		// READ it back and compare it.
		LabResult sameResult = dao.read(dummyResult.getId());
		assertEquals(MSG_RETRIEVAL_FAILED, dummyResult, sameResult);
		
		// UPDATE the same LabResult.
		dummyResult.setValue(dummyResult.getValue() + 1);
		
		assertTrue("Update failed.", dao.updateOrCreate(dummyResult));
		LabResult updatedResult = dao.read(dummyResult.getId());
		assertEquals("Update failed.", dummyResult, updatedResult);
		assertNotEquals("Update failed.", sameResult, updatedResult);
		
		// DELETE the LabResult.
		assertTrue(MSG_DELETION_FAILED, dao.delete(dummyResult));
	}

	@Override
	public LabResultDao getDao() {
		return dao;
	}
}
