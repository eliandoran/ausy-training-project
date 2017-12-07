package com.labplan.tests;

import java.util.Date;
import org.junit.BeforeClass;
import com.labplan.entities.LabList;
import com.labplan.entities.Patient;
import com.labplan.entities.generic.LazyLoadedEntity;
import com.labplan.persistence.sql.LabListDao;
import com.labplan.persistence.sql.PatientDao;
import com.labplan.tests.helpers.CrudTester;

public class LabListDaoTests extends CrudTester<Integer, LabList, LabListDao> {
	private static LabListDao dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new LabListDao();

		System.setProperty("java.util.logging.config.file",
				ClassLoader.getSystemResource("logging.properties").getPath());
	}

	@Override
	public LabList getRandomEntity() {
		LabList list = new LabList();
		
		// Generate a random patient.
		PatientDaoTests patientDaoTests = new PatientDaoTests();
		PatientDao patientDao = new PatientDao();
		
		Patient patient = patientDaoTests.getRandomEntity();
		Integer patientId = patientDao.create(patient);
		patient.setId(patientId);
		
		list.setPatient(new LazyLoadedEntity<>(patient));
		
		// Generate a random creation date.
		list.setCreationDate(new Date());
		
		return list;
	}

	@Override
	public LabListDao getDao() {
		return dao;
	}

}
