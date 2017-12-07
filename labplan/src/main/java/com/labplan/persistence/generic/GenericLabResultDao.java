package com.labplan.persistence.generic;

import java.util.Set;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.generic.CompositeKeyPair;
import com.labplan.entities.generic.LazyLoadedEntity;

/**
 * An interface which implements a Data Access Object (DAO) with CRUD (Create,
 * Read, Update, Delete) functionality for the {@link LabResult} entity.
 * 
 * @author adoran
 *
 */
public interface GenericLabResultDao
	extends CrudInterface<LabResult, CompositeKeyPair<
										LazyLoadedEntity<Integer, LabTest>,
										LazyLoadedEntity<Integer, LabList>>>
{
	/**
	 * Obtains all {@link LabResult} from a data source which belong to the given {@link LabList}.
	 * @param list	The {@link LabTest} for searching its {@link LabResult}s.
	 * @return A list of all {@link LabResult} which belong to {@code list}.
	 */
	Set<LabResult> readAll(LabList list);
}
