package com.labplan.persistence.sql;

import static com.labplan.helpers.TestMessages.MSG_INSERTION_FAILED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Random;
import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.Patient;
import com.labplan.helpers.DaoTester;
import com.labplan.persistence.DatabaseConnectionFactory;

public class SqlPatientDaoTest extends DaoTester<Integer, Patient, SqlPatientDao> {
	private static SqlPatientDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DatabaseConnectionFactory.setProfile("test");

		dao = new SqlPatientDao();

		System.setProperty("java.util.logging.config.file",
				ClassLoader.getSystemResource("logging.properties").getPath());
	}

	@Test
	public void testGetByName() {
		// CREATE a random patient.
		SqlPatientDao patientDao = getDao();
		Patient dummyPatient = getRandomEntity();

		dummyPatient.setId(patientDao.create(dummyPatient));
		assertNotNull(MSG_INSERTION_FAILED, dummyPatient.getId());

		// READ the same patient, but searching by its name. Then compare it with its
		// counterpart.
		Patient samePatient = patientDao.read(dummyPatient.getFirstName(), dummyPatient.getLastName());
		assertEquals(dummyPatient, samePatient);

		// DELETE the generated patient.
		patientDao.delete(dummyPatient);
	}

	@Test
	public void testInexistentGetByName() {
		SqlPatientDao patientDao = getDao();

		assertNull(patientDao.read(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
	}

	@Override
	public Patient getRandomEntity() {
		Random random = new Random();

		return new Patient(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 10 + random.nextInt(80),
				150 + random.nextInt(70), 50 + random.nextInt(100));
	}

	@Override
	public SqlPatientDao getDao() {
		return dao;
	}
}
