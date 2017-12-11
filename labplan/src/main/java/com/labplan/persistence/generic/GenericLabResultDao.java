package com.labplan.persistence.generic;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.generic.LazyLoadedEntity;

/**
 * An interface which implements a Data Access Object (DAO) with a parameterized
 * CRUD (Create, Read, Update, Delete) functionality for the {@link LabResult} entity.
 * 
 * <p><em>The creation, reading, update and deletion of a {@link LabResult} is dependent on its parent {@link LabList}.
 * Due to this, {@linkplain GenericLabResultDao} is not compatible with {@link Dao}.
 * </em></p>
 * 
 * @author Elian Doran
 *
 */
public interface GenericLabResultDao extends ChildDao<LabList, LabResult, LazyLoadedEntity<Integer, LabTest>>
{
	boolean updateOrCreate(LabResult entity);
}
