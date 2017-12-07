package com.labplan.persistence.generic;

import com.labplan.entities.Patient;

/**
 * An interface which implements a Data Access Object (DAO) with CRUD (Create,
 * Read, Update, Delete) functionality for the {@link Patient} entity.
 * 
 * @author adoran
 *
 */
public interface GenericPatientDao extends CrudInterface<Patient, Integer> {
	/**
	 * Obtains a single {@link Patient} from a data source, searching by their name.
	 * 
	 * @param firstName
	 *            The first name of the patient.
	 * @param lastName
	 *            The last name of the patient.
	 * @return The {@link Patient} whose name matches.
	 */
	Patient read(String firstName, String lastName);
}
