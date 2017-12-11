package com.labplan.persistence.generic;

import java.util.Set;

import com.labplan.entities.LabList;
import com.labplan.entities.Patient;

/**
 * An interface which implements a Data Access Object (DAO) with CRUD (Create,
 * Read, Update, Delete) functionality for the {@link LabList} entity.
 * 
 * @author Elian Doran
 *
 */
public interface LabListDao extends Dao<LabList, Integer> {
	LabList read(Integer key, boolean loadResults);

	/**
	 * Obtains a list of all the {@link LabList} which belong to the given
	 * {@link Patient}.
	 * 
	 * @param patient
	 *            The {@link Patient} whose {@link LabList} to look for.
	 * @return A {@link Set} containing the {@link LabList} which belong to the
	 *         given {@link Patient}.
	 */
	Set<LabList> readAllByPatient(Patient patient);
}
