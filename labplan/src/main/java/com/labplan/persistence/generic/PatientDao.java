package com.labplan.persistence.generic;

import com.labplan.entities.*;

public interface PatientDao extends CrudInterface<Patient, Integer> {
	Patient read(String firstName, String lastName);
}
