package com.labplan.tests;

import java.util.UUID;
import org.junit.BeforeClass;
import com.labplan.entities.LabTest;
import com.labplan.persistence.sql.LabTestDao;
import com.labplan.tests.helpers.CrudTester;

public class LabTestDaoTests extends CrudTester<Integer, LabTest, LabTestDao> {
	private static LabTestDao dao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao = new LabTestDao();

		System.setProperty("java.util.logging.config.file",
				ClassLoader.getSystemResource("logging.properties").getPath());
	}

	@Override
	public LabTest getRandomEntity() {
		return new LabTest(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 1.0f, 2.5f);
	}

	@Override
	public LabTestDao getDao() {
		return dao;
	}
}
