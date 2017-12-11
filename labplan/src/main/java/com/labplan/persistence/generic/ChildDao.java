package com.labplan.persistence.generic;

import com.labplan.entities.generic.Entity;

/**
 * An interface which describes a Data Access Object (DAO) which implements the
 * Create, Read, Update and Delete (CRUD) functionality for a given entity.
 * 
 * <p>
 * Unlike a {@link Dao}, entities of a {@link ChildDao} have a parent
 * {@link Entity}. These entities cannot exist outside the context of their
 * parent entity.
 * </p>
 * 
 * @author Elian Doran
 *
 * @param <TParentEntity>
 *            A type which represents the parent entity of the
 *            {@code TChildEntity}.
 * @param <TChildEntity>
 *            A type which represents the actual entity, child of
 *            {@code TParentEntity}.
 * @param <TChildKey>
 *            The type which represents the key of the {@code TChildEntity}.
 */
public interface ChildDao<TParentEntity extends Entity<?>, TChildEntity extends Entity<TChildKey>, TChildKey>
		extends Dao<TChildEntity, TChildKey> {
	/**
	 * Obtains the parent {@link Entity} of this entity.
	 * <p>
	 * See class description for more details.
	 * </p>
	 * 
	 * @return The parent {@link Entity} of this entity.
	 * @see ChildDao
	 */
	public TParentEntity getParentEntity();
}
