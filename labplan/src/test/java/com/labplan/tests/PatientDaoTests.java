package com.labplan.tests;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.Patient;
import com.labplan.persistence.PatientDao;

public class PatientDaoTests {
	private static PatientDao patientDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		patientDao = new PatientDao();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testInsertion() {
		Patient samePatient;
		Patient dummyPatient = new Patient("John", "Doe", 36, 180, 84);
		
		assertTrue("SQL insertion for single Patient failed.", patientDao.insertPatient(dummyPatient));
		assertNotNull("Generated index retrieval for single Patient failed.", dummyPatient.getId());
		
		samePatient = patientDao.getPatientById(dummyPatient.getId());
		assertNotNull("SQL retrieval for single Patient failed.", samePatient);
		assertTrue("Dummy patient and retrieved patient are not identical.", dummyPatient.equals(samePatient));
		
		assertTrue("SQL deletion for single Patient failed.", patientDao.deletePatient(dummyPatient));
	}
	
	@Test
	public void testGetAll() {
		Patient dummyPatient1 = new Patient("Jane", "Doe", 34, 165, 54);
		Patient dummyPatient2 = new Patient("John", "Doe", 36, 180, 84);
		
		assertTrue("SQL insertion for single Patient failed.", patientDao.insertPatient(dummyPatient1));
		assertTrue("SQL insertion for single Patient failed.", patientDao.insertPatient(dummyPatient2));
		
		Set<Patient> patients = patientDao.getAllPatients();
		assertTrue("Inserted patient1 not found in GetAll()", patients.contains(dummyPatient1));
		assertTrue("Inserted patient2 not found in GetAll()", patients.contains(dummyPatient2));
		
		assertTrue("SQL deletion for single Patient failed.", patientDao.deletePatient(dummyPatient1));
		assertTrue("SQL deletion for single Patient failed.", patientDao.deletePatient(dummyPatient2));
	}
	
	@Test
	public void testGetByName() {
		Patient dummyPatient = new Patient("John", "Doe", 36, 180, 84);
		assertTrue("SQL insertion for single Patient failed.", patientDao.insertPatient(dummyPatient));
		
		Patient samePatient = patientDao.getPatientByName(dummyPatient.getFirstName(), dummyPatient.getLastName());
		assertEquals(dummyPatient, samePatient);
	}
}
