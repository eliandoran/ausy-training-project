package com.labplan.persistence.generic;

import java.util.Set;

/**
 * An interface which describes a Data Access Object (DAO) which implements the
 * Create, Read, Update and Delete (CRUD) functionality for a given entity.
 * 
 * @author Elian Doran
 *
 * @param <TEntity>
 *            The entity or a model around which the CRUD functionality is
 *            implemented.
 * @param <TKey>
 *            The key used for identifying an entity, could be a mirror of the
 *            database table's primary keys.
 * 
 * @see com.labplan.persistence.sql.SqlPatientDao
 * @see com.labplan.persistence.generic.PatientDao
 */
public interface Dao<TEntity, TKey> {
	/**
	 * Obtains a single entity from a data source.
	 * 
	 * @param key
	 *            The key used for identifying the entity.
	 * @return The entity as read from the data source.
	 */
	public TEntity read(TKey key);

	/**
	 * Obtains all the entities from a data source.
	 * 
	 * @return A {@link Set<>} containing all the entities from the data source.
	 */
	public Set<TEntity> readAll();

	/**
	 * Inserts the given {@code entity} into the data source.
	 * 
	 * @param entity
	 *            The entity to be inserted into the data source.
	 * @return The key of the inserted entity (i.e. primary key after insertion in
	 *         database).
	 */
	public TKey create(TEntity entity);

	/**
	 * Updates an existing entity from the data source with the provided data. The
	 * keys of both the entity from the data source and the given entity must be
	 * identical for the update to work.
	 * 
	 * @param entity
	 *            The new entity to replace the existing entity.
	 * @return {@code true} if the update succeeded, {@code false} otherwise (entity
	 *         does not exist, connection failure).
	 */
	public boolean update(TEntity entity);

	/**
	 * Removes an existing entry from the data source. Only the keys of the data
	 * source entity and the given entity must match in order for the deletion to
	 * work.
	 * 
	 * @param entity
	 *            The entity to delete from the data source.
	 * @return {@code true} if the deletion succeeded, {@code false} otherwise
	 *         (entity does not exist, connection failure).
	 */
	public boolean delete(TEntity entity);
}
