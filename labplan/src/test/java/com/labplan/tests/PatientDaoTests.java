package com.labplan.tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.Patient;
import com.labplan.persistence.PatientDao;

public class PatientDaoTests {
	private static Patient dummyPatient;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dummyPatient = new Patient("John", "Doe", 36, 180, 84);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testInsertion() {
		PatientDao dao = new PatientDao();
		Patient samePatient;
		
		assertTrue("SQL insertion for single Patient failed.", dao.insertPatient(dummyPatient));
		assertNotNull("Generated index retrieval for single Patient failed.", dummyPatient.getId());
		
		samePatient = dao.getPatientById(dummyPatient.getId());
		assertNotNull("SQL retrieval for single Patient failed.", samePatient);
		
		assertTrue("SQL deletion for single Patient failed.", dao.deletePatient(dummyPatient));
	}

}
