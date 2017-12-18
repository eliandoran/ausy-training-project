package com.labplan.persistence.generic;

import java.util.List;

import com.labplan.entities.Patient;

/**
 * An interface which implements a Data Access Object (DAO) with CRUD (Create,
 * Read, Update, Delete) functionality for the {@link Patient} entity.
 * 
 * @author Elian Doran
 *
 */
public interface PatientDao extends Dao<Patient, Integer> {
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

	/**
	 * Obtains a maximum of {@code limit} {@link Patient}s read from a data source,
	 * while skipping the first {@code offset} entities.
	 * 
	 * @param limit
	 *            The maximum number of {@link Patient} to return.
	 * @param offset
	 *            The number of {@link Patient} to skip.
	 * @return A {@link List} of {@link Patient} obtained form the data source.
	 */
	List<Patient> read(Integer limit, Integer offset);

	/**
	 * Obtains the total number of {@link Patient}s from the data source.
	 * 
	 * @return The total number of {@link Patient}s from the data source.
	 */
	Integer getPatientsCount();

	/**
	 * Removes all {@link Patient} from the data source.
	 */
	boolean truncate();
}
