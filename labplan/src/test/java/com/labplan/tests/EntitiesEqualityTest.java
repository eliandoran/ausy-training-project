package com.labplan.tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.labplan.entities.*;

public class EntitiesEqualityTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testEqualPatients() {
		Patient a = new Patient(2, "John", "Doe", 10, 170, 90);
		Patient b = new Patient(2, "John", "Doe", 10, 170, 90);
		
		assertTrue(a.equals((Object)b));
	}

	@Test
	public void testUnequalPatients() {
		Patient a = new Patient(2, "John", "Doe", 10, 170, 90);
		Patient b = new Patient(2, "John", "Doe", 10, 170, 100);
		
		assertFalse(a.equals((Object)b));
	}
	
	@Test
	public void testEqualLabTests() {
		LabTest a = new LabTest(5, "Blood test", "A series of blood tests", 1.25f, 2.75f);
		LabTest b = new LabTest(5, "Blood test", "A series of blood tests", 1.25f, 2.75f);
		
		assertTrue(a.equals((Object)b));
	}
	
	@Test
	public void testUnequalLabTests() {
		LabTest a = new LabTest(5, "Blood test", "A series of blood tests", 1.26f, 2.75f);
		LabTest b = new LabTest(5, "Blood test", "A series of blood tests", 1.25f, 2.75f);
		
		assertFalse(a.equals((Object)b));
	}
}
