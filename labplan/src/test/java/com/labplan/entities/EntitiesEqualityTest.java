package com.labplan.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class EntitiesEqualityTest {
	@Test
	public void testEqualPatients() {
		Patient a = new Patient(2, "John", "Doe", 10, 170, 90);
		Patient b = new Patient(2, "John", "Doe", 10, 170, 90);

		assertTrue(a.equals(b));
	}

	@Test
	public void testUnequalPatients() {
		Patient a = new Patient(2, "John", "Doe", 10, 170, 90);
		Patient b = new Patient(2, "John", "Doe", 10, 170, 100);

		assertFalse(a.equals(b));
	}

	@Test
	public void testEqualLabTests() {
		LabTest a = new LabTest(5, "Blood test", "A series of blood tests", 1.25f, 2.75f);
		LabTest b = new LabTest(5, "Blood test", "A series of blood tests", 1.25f, 2.75f);

		assertTrue(a.equals(b));
	}

	@Test
	public void testUnequalLabTests() {
		LabTest a = new LabTest(5, "Blood test", "A series of blood tests", 1.26f, 2.75f);
		LabTest b = new LabTest(5, "Blood test", "A series of blood tests", 1.25f, 2.75f);

		assertFalse(a.equals(b));
	}
}
