package com.labplan.persistence.sql;

import static com.labplan.helpers.TestMessages.MSG_DELETION_FAILED;
import static com.labplan.helpers.TestMessages.MSG_INSERTION_FAILED;
import static com.labplan.helpers.TestMessages.MSG_RETRIEVAL_FAILED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.Patient;
import com.labplan.entities.generic.LazyLoadedEntity;
import com.labplan.helpers.DaoTester;
import com.labplan.persistence.DatabaseConnectionFactory;

public class LabListDaoTests extends DaoTester<Integer, LabList, SqlLabListDao> {
	private static SqlLabListDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionFactory.setProfile("test");
		dao = new SqlLabListDao();

		System.setProperty("java.util.logging.config.file",
				ClassLoader.getSystemResource("logging.properties").getPath());
	}
	
	@Test
	public void testPatientLazyLoading() {
		// CREATE a random lab list.
		LabList dummyLabList = getRandomEntity();
		dummyLabList.setId(dao.create(dummyLabList));
		assertNotNull(MSG_INSERTION_FAILED, dummyLabList.getId());
		
		// READ the lab list back.
		LabList sameLabList = dao.read(dummyLabList.getId());
		assertNotNull(MSG_RETRIEVAL_FAILED, sameLabList);
		
		// Check to see whether patient is not null.
		assertNotNull("Patient should not be null.", sameLabList.getPatient());
		
		// Check to see that the patient has not been yet loaded (lazy loading).
		assertTrue(!sameLabList.getPatient().getIsLoaded());
		
		// Load the patient and compare it against the generated Patient.
		Patient readPatient = sameLabList.getPatient().getEntity();
		assertTrue(sameLabList.getPatient().getIsLoaded());
		assertEquals(dummyLabList.getPatient().getEntity(), readPatient);
		
		// DELETE the generated lab list.
		assertTrue(MSG_DELETION_FAILED, dao.delete(dummyLabList));
	}
	
	@Test
	public void testReadAllByPatient() {
		// CREATE a random patient.
		SqlPatientDao patientDao = new SqlPatientDao();
		PatientDaoTests patientDaoTests = new PatientDaoTests();
		
		Patient dummyPatient = patientDaoTests.getRandomEntity();
		dummyPatient.setId(patientDao.create(dummyPatient));
		assertNotNull(MSG_INSERTION_FAILED, dummyPatient.getId());
		
		// CREATE a few random lab list.
		List<LabList> lists = new LinkedList<>();
		
		for (int i=0; i<3; i++) {
			LabList dummyLabList = getRandomEntity();
			dummyLabList.setPatient(new LazyLoadedEntity<>(dummyPatient));
			dummyLabList.setId(dao.create(dummyLabList));
			assertNotNull(MSG_INSERTION_FAILED, dummyLabList.getId());
			
			lists.add(dummyLabList);
		}
		
		// READ all by patient.
		Set<LabList> sameLists = dao.readAllByPatient(dummyPatient);
		
		assertTrue("List is empty.", !sameLists.isEmpty());
		
		for (LabList labList : lists) {
			assertTrue("Not found.", sameLists.contains(labList));
		}
	}
	
	@Test
	public void testResultsInsertion() {
		LabList dummyLabList = getRandomEntity();
		
		// Insert multiple LabResult.
		dummyLabList.setResults(new LinkedList<LabResult>());
		
		LabResultDaoTests resultDaoTests = new LabResultDaoTests();
		
		for (int i=0; i<5; i++) {
			LabResult randomResult = resultDaoTests.getRandomEntity();
			dummyLabList.getResults().add(randomResult);
		}
		
		// INSERT the LabList.
		dummyLabList.setId(dao.create(dummyLabList));
		assertNotNull(MSG_INSERTION_FAILED, dummyLabList.getId());
		
		// READ back the LabList.
		LabList sameLabList = dao.read(dummyLabList.getId(), true);
		
		for (LabResult result : dummyLabList.getResults()) {
			assertTrue(sameLabList.getResults().contains(result));
		}
	}

	@Override
	public LabList getRandomEntity() {
		LabList list = new LabList();
		
		// Generate a random patient.
		PatientDaoTests patientDaoTests = new PatientDaoTests();
		SqlPatientDao patientDao = new SqlPatientDao();
		
		Patient patient = patientDaoTests.getRandomEntity();
		patient.setId(patientDao.create(patient));
		assertNotNull(MSG_INSERTION_FAILED, patient.getId());
		
		list.setPatient(new LazyLoadedEntity<>(patient));
		
		// Generate a random creation date.
		list.setCreationDate(new Date());
		
		return list;
	}

	@Override
	public SqlLabListDao getDao() {
		return dao;
	}

}
