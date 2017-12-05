package com.labplan.tests;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.Patient;
import com.labplan.persistence.sql.PatientDao;
import com.labplan.tests.helpers.CrudTester;

public class PatientDaoTests extends CrudTester<Integer, Patient, PatientDao> {
	private static PatientDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new PatientDao();

		System.setProperty("java.util.logging.config.file",
				ClassLoader.getSystemResource("logging.properties").getPath());
	}

	@Test
	public void testGetByName() {
		// CREATE a random patient.
		PatientDao patientDao = getDao();
		Patient dummyPatient = getRandomEntity();

		Integer patientId = patientDao.create(dummyPatient);
		assertNotNull("SQL insertion for single Patient failed.", patientId);
		dummyPatient.setId(patientId);

		// READ the same patient, but searching by its name. Then compare it with its counterpart.
		Patient samePatient = patientDao.read(dummyPatient.getFirstName(), dummyPatient.getLastName());
		assertEquals(dummyPatient, samePatient);

		// DELETE the generated patient.
		patientDao.delete(dummyPatient);
	}

	@Test
	public void testInexistentGetByName() {
		PatientDao patientDao = getDao();

		assertNull(patientDao.read(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
	}

	@Override
	public Patient getRandomEntity() {
		return new Patient(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 12, 160, 80);
	}

	@Override
	public PatientDao getDao() {
		return dao;
	}
}
