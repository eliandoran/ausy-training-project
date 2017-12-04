package com.labplan.tests;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.Patient;
import com.labplan.persistence.sql.PatientDao;

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
		
		assertTrue("SQL insertion for single Patient failed.", patientDao.insert(dummyPatient));
		assertNotNull("Generated index retrieval for single Patient failed.", dummyPatient.getId());
		
		samePatient = patientDao.get(dummyPatient.getId());
		assertNotNull("SQL retrieval for single Patient failed.", samePatient);
		assertTrue("Dummy patient and retrieved patient are not identical.", dummyPatient.equals(samePatient));
		
		assertTrue("SQL deletion for single Patient failed.", patientDao.delete(dummyPatient));
	}
	
	@Test
	public void testGetAll() {
		Patient dummyPatient1 = new Patient("Jane", "Doe", 34, 165, 54);
		Patient dummyPatient2 = new Patient("John", "Doe", 36, 180, 84);
		
		assertTrue("SQL insertion for single Patient failed.", patientDao.insert(dummyPatient1));
		assertTrue("SQL insertion for single Patient failed.", patientDao.insert(dummyPatient2));
		
		Set<Patient> patients = patientDao.getAll();
		assertTrue("Inserted patient1 not found in GetAll()", patients.contains(dummyPatient1));
		assertTrue("Inserted patient2 not found in GetAll()", patients.contains(dummyPatient2));
		
		assertTrue("SQL deletion for single Patient failed.", patientDao.delete(dummyPatient1));
		assertTrue("SQL deletion for single Patient failed.", patientDao.delete(dummyPatient2));
	}
	
	@Test
	public void testUpdate() {
		Patient dummyPatient = new Patient("Jane", "Doe", 34, 165, 54);
		assertTrue("SQL insertion for single Patient failed.", patientDao.insert(dummyPatient));
		
		dummyPatient.setLastName("Smith");
		assertTrue("SQL update for single Patient failed.", patientDao.update(dummyPatient));
		
		Patient samePatient = patientDao.get(dummyPatient.getFirstName(), dummyPatient.getLastName());
		assertEquals(dummyPatient, samePatient);
		
		assertTrue("SQL deletion for single Patient failed.", patientDao.delete(dummyPatient));
	}
	
	@Test
	public void testGetByName() {
		Patient dummyPatient = new Patient(
				UUID.randomUUID().toString(),
				UUID.randomUUID().toString(),
				36, 180, 84);
		
		assertTrue("SQL insertion for single Patient failed.", patientDao.insert(dummyPatient));
		
		Patient samePatient = patientDao.get(dummyPatient.getFirstName(), dummyPatient.getLastName());
		assertEquals(dummyPatient, samePatient);
		
		patientDao.delete(dummyPatient);
	}
	
	@Test
	public void testInexistentGetByName() {
		assertNull(patientDao.get(
				UUID.randomUUID().toString(),
				UUID.randomUUID().toString()));
	}
}
