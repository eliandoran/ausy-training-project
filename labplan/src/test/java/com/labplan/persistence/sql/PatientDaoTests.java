package com.labplan.persistence.sql;

import static com.labplan.helpers.TestMessages.MSG_INSERTION_FAILED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.Patient;
import com.labplan.helpers.CrudTester;
import com.labplan.persistence.DatabaseConnectionFactory;

public class PatientDaoTests extends CrudTester<Integer, Patient, PatientDao> {
	private static PatientDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionFactory.setProfile("test");
		
		dao = new PatientDao();

		System.setProperty("java.util.logging.config.file",
				ClassLoader.getSystemResource("logging.properties").getPath());
	}

	@Test
	public void testGetByName() {
		// CREATE a random patient.
		PatientDao patientDao = getDao();
		Patient dummyPatient = getRandomEntity();

		dummyPatient.setId(patientDao.create(dummyPatient));
		assertNotNull(MSG_INSERTION_FAILED, dummyPatient.getId());

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
