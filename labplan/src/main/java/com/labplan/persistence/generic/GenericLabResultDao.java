package com.labplan.persistence.generic;

import java.util.Set;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.generic.LazyLoadedEntity;

/**
 * An interface which implements a Data Access Object (DAO) with a parameterized
 * CRUD (Create, Read, Update, Delete) functionality for the {@link LabResult} entity.
 * 
 * <p><em>The creation, reading, update and deletion of a {@link LabResult} is dependent on its parent {@link LabList}.
 * Due to this, {@linkplain GenericLabResultDao} is not compatible with {@link CrudInterface}.
 * </em></p>
 * 
 * @author adoran
 *
 */
public interface GenericLabResultDao extends DependentCrudInterface<LabList, LabResult, LazyLoadedEntity<Integer, LabTest>>
{
	
}
