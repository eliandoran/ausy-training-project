package com.labplan.persistence.generic;

import com.labplan.entities.LabTest;

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
	 * @param name The full name of the lab test.
	 * @return The {@link TabTest} with a matching name.
	 */
	LabTest read(String name);
}
