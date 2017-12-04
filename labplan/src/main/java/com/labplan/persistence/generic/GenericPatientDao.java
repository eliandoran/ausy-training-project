package com.labplan.persistence.generic;

import java.util.Set;
import com.labplan.entities.*;
import com.labplan.persistence.exceptions.ConnectionFailedException;

public interface GenericPatientDao {
	Patient get(int id);
	Patient get(String firstName, String lastName);
	Set<Patient> getAll();
	boolean insert(Patient patient);
	boolean update(Patient patient);
	boolean delete(Patient patient);
}
