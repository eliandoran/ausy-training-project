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
		
		Integer patientId = patientDao.create(dummyPatient);
		assertNotNull("SQL insertion for single Patient failed.", patientId);
		dummyPatient.setId(patientId);
		
		samePatient = patientDao.read(dummyPatient.getId());
		assertNotNull("SQL retrieval for single Patient failed.", samePatient);
		assertTrue("Dummy patient and retrieved patient are not identical.", dummyPatient.equals(samePatient));
		
		assertTrue("SQL deletion for single Patient failed.", patientDao.delete(dummyPatient));
	}
	
	@Test
	public void testGetAll() {
		Patient dummyPatient1 = new Patient("Jane", "Doe", 34, 165, 54);
		Patient dummyPatient2 = new Patient("John", "Doe", 36, 180, 84);
		
		Integer dummyPatientId1 = patientDao.create(dummyPatient1);
		Integer dummyPatientId2 = patientDao.create(dummyPatient2);
		
		assertNotNull("SQL insertion for single Patient failed.", dummyPatientId1);
		assertNotNull("SQL insertion for single Patient failed.", dummyPatientId2);
		
		dummyPatient1.setId(dummyPatientId1);
		dummyPatient2.setId(dummyPatientId2);
		
		Set<Patient> patients = patientDao.readAll();
		assertTrue("Inserted patient1 not found in GetAll()", patients.contains(dummyPatient1));
		assertTrue("Inserted patient2 not found in GetAll()", patients.contains(dummyPatient2));
		
		assertTrue("SQL deletion for single Patient failed.", patientDao.delete(dummyPatient1));
		assertTrue("SQL deletion for single Patient failed.", patientDao.delete(dummyPatient2));
	}
	
	@Test
	public void testUpdate() {
		Patient dummyPatient = new Patient("Jane", "Doe", 34, 165, 54);
		Integer patientId = patientDao.create(dummyPatient);
		assertNotNull("SQL insertion for single Patient failed.", patientId);
		dummyPatient.setId(patientId);
		
		dummyPatient.setLastName("Smith");
		assertTrue("SQL update for single Patient failed.", patientDao.update(dummyPatient));
		
		Patient samePatient = patientDao.read(dummyPatient.getFirstName(), dummyPatient.getLastName());
		assertEquals(dummyPatient, samePatient);
		
		assertTrue("SQL deletion for single Patient failed.", patientDao.delete(dummyPatient));
	}
	
	@Test
	public void testGetByName() {
		Patient dummyPatient = new Patient(
				UUID.randomUUID().toString(),
				UUID.randomUUID().toString(),
				36, 180, 84);
		
		Integer patientId = patientDao.create(dummyPatient);
		assertNotNull("SQL insertion for single Patient failed.", patientId);
		dummyPatient.setId(patientId);
		
		Patient samePatient = patientDao.read(dummyPatient.getFirstName(), dummyPatient.getLastName());
		assertEquals(dummyPatient, samePatient);
		
		patientDao.delete(dummyPatient);
	}
	
	@Test
	public void testInexistentGetByName() {
		assertNull(patientDao.read(
				UUID.randomUUID().toString(),
				UUID.randomUUID().toString()));
	}
}
