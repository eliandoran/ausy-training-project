package com.labplan.services;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.Patient;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.persistence.sql.SqlPatientDaoTest;

import static com.labplan.helpers.TestMessages.*;

public class PatientServiceTest {
	private static PatientDao patientDao;
	private static PatientService patientService;
	
	@BeforeClass
	public static void setUpBeforeClass() {
		patientDao = new SqlPatientDao();
		patientService = new PatientService(patientDao);
	}
	
	@Test
	public void testGetPage() {
		// TRUNCATE the data source.
		assertTrue(MSG_TRUNCATE_FAILED, patientDao.truncate());
		
		// CREATE a number of patients.
		SqlPatientDaoTest patientDaoTests = new SqlPatientDaoTest();
		List<Patient> generatedPatients = new LinkedList<>();
		
		for (int i=0; i<5; i++) {
			Patient randomPatient = patientDaoTests.getRandomEntity();
			randomPatient.setId(patientDao.create(randomPatient));
			assertNotNull(MSG_INSERTION_FAILED, randomPatient.getId());
			
			generatedPatients.add(randomPatient);
		}
		
		// READ paginated.
		int patientCount = generatedPatients.size();
		int entriesPerPage = 2;
		int pageCount = Math.round((float)patientCount / entriesPerPage);
		
		assertEquals(pageCount, (int)patientService.getPageCount(entriesPerPage));
		
		List<Patient> samePatients = new LinkedList<>();
		
		for (int pageNum=1; pageNum <= pageCount; pageNum++) {
			List<Patient> page = patientService.getPage(pageNum, entriesPerPage);
			samePatients.addAll(page);
			
			assertTrue("Page should have less entries.", page.size() <= entriesPerPage);
			assertTrue("Page should not have zero length.", page.size() > 0);
		}
		
		assertEquals(generatedPatients.size(), samePatients.size());
		
		for (Patient patient : generatedPatients) {
			assertTrue("Generated entry not found in paginated results.", samePatients.contains(patient));
		}
	}
}
