package com.labplan.persistence.generic;

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
	
}
