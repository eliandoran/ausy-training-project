package com.labplan.persistence.generic;

import java.util.List;
import java.util.Set;

import com.labplan.entities.LabList;
import com.labplan.entities.LabTest;
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
	
	/**
	 * Obtains a maximum of {@code limit} {@link LabTest}s read from a data source,
	 * while skipping the first {@code offset} entities.
	 * 
	 * @param limit
	 *            The maximum number of {@link LabTest} to return.
	 * @param offset
	 *            The number of {@link LabTest} to skip.
	 * @return A {@link List} of {@link LabTest} obtained form the data source.
	 */
	List<LabList> read(Integer limit, Integer offset);

	/**
	 * Obtains the total number of {@link Patient}s from the data source.
	 * 
	 * @return The total number of {@link Patient}s from the data source.
	 */
	Integer getCount();
}
