package com.labplan.tests;

import java.util.Random;

import org.junit.BeforeClass;

import com.labplan.entities.CompositeKeyPair;
import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.persistence.sql.LabListDao;
import com.labplan.persistence.sql.LabResultDao;
import com.labplan.persistence.sql.LabTestDao;
import com.labplan.tests.helpers.CrudTester;

public class LabResultDaoTests extends CrudTester<CompositeKeyPair<LabTest, LabList>, LabResult, LabResultDao> {
	private static LabResultDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new LabResultDao();

		System.setProperty("java.util.logging.config.file",
				ClassLoader.getSystemResource("logging.properties").getPath());
	}

	@Override
	public LabResult getRandomEntity() {
		LabResult result = new LabResult();
		
		// Generate a random list for the result
		LabListDaoTests labListDaoTests = new LabListDaoTests();
		LabListDao labListDao = new LabListDao();
		
		LabList dummyLabList = labListDaoTests.getRandomEntity();
		Integer dummyLabListId = labListDao.create(dummyLabList);
		dummyLabList.setId(dummyLabListId);
		
		// Generate a random test for the result
		LabTestDaoTests labTestDaoTests = new LabTestDaoTests();
		LabTestDao labTestDao = new LabTestDao();
		
		LabTest dummyLabTest = labTestDaoTests.getRandomEntity();
		Integer dummyLabTestId = labTestDao.create(dummyLabTest);
		dummyLabTest.setId(dummyLabTestId);
		
		result.setId(new CompositeKeyPair<>(dummyLabTest, dummyLabList));
		
		Random random = new Random();
		result.setValue(random.nextFloat());
		
		return result;
	}

	@Override
	public LabResultDao getDao() {
		return dao;
	}
}
