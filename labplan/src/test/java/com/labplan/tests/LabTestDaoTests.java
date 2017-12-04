package com.labplan.tests;

import static org.junit.Assert.*;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.LabTest;
import com.labplan.persistence.sql.LabTestDao;
import com.labplan.tests.helpers.CrudTester;

public class LabTestDaoTests extends CrudTester<Integer, LabTest, LabTestDao> {
	private static LabTestDao labTestDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		labTestDao = new LabTestDao();
		
		System.setProperty("java.util.logging.config.file", ClassLoader.getSystemResource("logging.properties").getPath());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Override
	public LabTest getRandomEntity() {
		return new LabTest(
				UUID.randomUUID().toString(),
				UUID.randomUUID().toString(),
				1.0f, 2.5f);
	}

	@Override
	public LabTestDao getDao() {
		return new LabTestDao();
	}
}
