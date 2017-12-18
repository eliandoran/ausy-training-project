package com.labplan.persistence.generic;

import java.util.List;

import com.labplan.entities.LabTest;
import com.labplan.entities.Patient;

/**
 * An interface which implements a Data Access Object (DAO) with CRUD (Create,
 * Read, Update, Delete) functionality for the {@link LabTest} entity.
 * 
 * @author Elian Doran
 *
 */
public interface LabTestDao extends Dao<LabTest, Integer> {
	/**
	 * Obtains a single {@link LabTest} from a data source, searching by its name.
	 * 
	 * @param name
	 *            The full name of the lab test.
	 * @return The {@link TabTest} with a matching name.
	 */
	LabTest read(String name);
	
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
	List<LabTest> read(Integer limit, Integer offset);

	/**
	 * Obtains the total number of {@link Patient}s from the data source.
	 * 
	 * @return The total number of {@link Patient}s from the data source.
	 */
	Integer getCount();
}
