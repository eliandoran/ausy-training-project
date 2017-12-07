package com.labplan.tests.persistence.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.generic.CompositeKeyPair;
import com.labplan.entities.generic.LazyLoadedEntity;
import com.labplan.persistence.sql.LabListDao;
import com.labplan.persistence.sql.LabResultDao;
import com.labplan.persistence.sql.LabTestDao;
import com.labplan.tests.helpers.CrudTester;
import static com.labplan.tests.helpers.TestMessages.*;

public class LabResultDaoTests extends CrudTester<CompositeKeyPair<LazyLoadedEntity<Integer,LabTest>, LazyLoadedEntity<Integer,LabList>>, LabResult, LabResultDao> {
	private static LabResultDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new LabResultDao();

		System.setProperty("java.util.logging.config.file",
				ClassLoader.getSystemResource("logging.properties").getPath());
	}
	
	@Test
	public void testReadAllByLabList() {
		// Generate a random list
		LabListDaoTests listTests = new LabListDaoTests();
		LabListDao listDao = new LabListDao();
		LabList dummyList = listTests.getRandomEntity();
		
		// Insert the dummy list into the database.
		Integer dummyListId = listDao.create(dummyList);
		dummyList.setId(dummyListId);
		
		// Generate a number of random lab results.
		List<LabResult> generatedResults = new LinkedList<LabResult>();
		
		for (int i=0; i<5; i++) {
			LabResult result = getRandomEntity(dummyList);
			generatedResults.add(result);
			dao.create(result);
		}
		
		// Generate another lab result, this time belonging to another list.
		LabResult alienResult = getRandomEntity();
		dao.create(alienResult);
		
		// Check whether the entities are read back correctly.
		Set<LabResult> results = dao.readAll(dummyList);
		
		assertTrue("Generated list is empty.", !results.isEmpty());
		
		for (LabResult generatedResult : generatedResults) {
			boolean found = false;
			
			for (LabResult readResult : results) {
				if (readResult.equals(generatedResult)) {
					found = true;
					break;
				}
			}
			
			assertTrue("Generated entity not found.", found);
		}
		
		assertTrue("Entity should not be in this list.", !results.contains(alienResult));
		
		// DELETE all generated entities.
		assertTrue(MSG_DELETION_FAILED, dao.delete(alienResult));
		
		for (LabResult generatedResult : generatedResults) {
			assertTrue(MSG_DELETION_FAILED, dao.delete(generatedResult));
		}
		
		assertTrue(MSG_DELETION_FAILED, listDao.delete(dummyList));
	}
	
	@Test
	public void testKeyLazyLoading() {
		// CREATE a random lab result.
		LabResult dummyLabResult = getRandomEntity();
		dummyLabResult.setId(dao.create(dummyLabResult));
		assertNotNull(MSG_INSERTION_FAILED, dummyLabResult.getId());
		
		// READ the lab result back.
		LabResult sameLabResult = dao.read(dummyLabResult.getId());
		assertNotNull(MSG_RETRIEVAL_FAILED, sameLabResult);
		
		// Check to see whether lazy entities are not null.
		assertNotNull("Entity ID should not be null.", sameLabResult.getId());
		assertNotNull("LabTest from ID should not be null.", sameLabResult.getId().getFirstKey());
		assertNotNull("LabList from ID should not be null.", sameLabResult.getId().getSecondKey());
		
		// Check to see that the lazy entities are not yet loaded.
		assertTrue(!sameLabResult.getId().getFirstKey().getIsLoaded());
		assertTrue(!sameLabResult.getId().getSecondKey().getIsLoaded());
		
		// Load the read LabTest and compare it against the initial one.
		LabTest readLabTest = sameLabResult.getId().getFirstKey().getEntity();
		assertTrue(sameLabResult.getId().getFirstKey().getIsLoaded());
		assertEquals(dummyLabResult.getId().getFirstKey().getEntity(), readLabTest);
		
		// Load the read LabResult and compare it against the initial one.
		LabList readLabList = sameLabResult.getId().getSecondKey().getEntity();
		assertTrue(sameLabResult.getId().getSecondKey().getIsLoaded());
		assertEquals(dummyLabResult.getId().getSecondKey().getEntity(), readLabList);
		
		// DELETE the generated lab result.
		assertTrue(MSG_DELETION_FAILED, dao.delete(dummyLabResult));
	}

	@Override
	public LabResult getRandomEntity() {
		// Generate a random list for the result
		LabListDaoTests labListDaoTests = new LabListDaoTests();
		LabListDao labListDao = new LabListDao();
		
		LabList dummyLabList = labListDaoTests.getRandomEntity();
		Integer dummyLabListId = labListDao.create(dummyLabList);
		dummyLabList.setId(dummyLabListId);
		
		return getRandomEntity(dummyLabList);
	}
	
	private LabResult getRandomEntity(LabList list) {
		LabResult result = new LabResult();
		
		// Generate a random test for the result
		LabTestDaoTests labTestDaoTests = new LabTestDaoTests();
		LabTestDao labTestDao = new LabTestDao();
		
		LabTest dummyLabTest = labTestDaoTests.getRandomEntity();
		Integer dummyLabTestId = labTestDao.create(dummyLabTest);
		dummyLabTest.setId(dummyLabTestId);
		
		LazyLoadedEntity<Integer, LabTest> lazyTest = new LazyLoadedEntity<Integer, LabTest>(dummyLabTest);
		LazyLoadedEntity<Integer, LabList> lazyList = new LazyLoadedEntity<Integer, LabList>(list);
		
		result.setId(new CompositeKeyPair<>(lazyTest, lazyList));
		
		Random random = new Random();
		result.setValue(random.nextFloat() * 10);
		
		return result;
	}

	@Override
	public LabResultDao getDao() {
		return dao;
	}
}
