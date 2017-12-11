package com.labplan.persistence.generic;

import com.labplan.entities.LabList;
import com.labplan.entities.LabResult;
import com.labplan.entities.LabTest;
import com.labplan.entities.generic.LazyLoadedEntity;

/**
 * An interface which implements a Data Access Object (DAO) with a parameterized
 * CRUD (Create, Read, Update, Delete) functionality for the {@link LabResult}
 * entity.
 * 
 * <p>
 * <em>The creation, reading, update and deletion of a {@link LabResult} is
 * dependent on its parent {@link LabList}. Due to this,
 * {@linkplain LabResultDao} is not compatible with {@link Dao}. </em>
 * </p>
 * 
 * @author Elian Doran
 *
 */
public interface LabResultDao extends ChildDao<LabList, LabResult, LazyLoadedEntity<Integer, LabTest>> {
	/**
	 * Adds the given {@link LabResult} into the data source. Should the
	 * {@link LabResult} already exist (identified by its key), then its values are
	 * updated instead.
	 * 
	 * @param entity
	 *            The {@link LabResult} to create or update.
	 * @return {@code true} if the update/create operation succeeded, {@code false}
	 *         otherwise.
	 */
	boolean updateOrCreate(LabResult entity);
}
