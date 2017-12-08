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
public interface GenericLabResultDao
{	
	/**
	 * Obtains all {@link LabResult} from a data source which belong to the given {@link LabList}.
	 * @param list	The {@link LabTest} for searching its {@link LabResult}s.
	 * @return A list of all {@link LabResult} which belong to {@code list}.
	 */
	Set<LabResult> read(LabList list);
	
	/**
	 * Inserts the given {@link LabResult} into the data source.
	 * @param list		The parent {@link LabList} of this {@link LabResult}.
	 * @param entity	The {@link LabResult} to be inserted.
	 * @return The key of the {@link LabResult} after insertion, a {@link LazyLoadedEntity} containing the corresponding {@link LabTest}.
	 */
	LazyLoadedEntity<Integer, LabTest> create(LabList list, LabResult entity);
	
	/**
	 * Updates an existing entity from the data source with the provided data. The
	 * keys of both the entity from the data source and the given entity must be
	 * identical for the update to work.
	 * 
	 * @param list		The parent {@link LabList} of this {@link LabResult}.
	 * @param entity	The {@link LabResult} to be updated.
	 * @return
	 */
	boolean update(LabList list, LabResult entity);
	
	/**
	 * Removes an existing entry from the data source. Only the keys of the data
	 * source entity and the given entity must match in order for the deletion to
	 * work.
	 * 
	 * @param list		The parent {@link LabList} of this {@link LabResult}. 
	 * @param entity	The {@link LabResult} to be deleted.
	 * @return
	 */
	boolean delete(LabList list, LabResult entity);
}
