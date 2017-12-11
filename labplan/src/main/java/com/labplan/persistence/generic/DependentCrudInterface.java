package com.labplan.persistence.generic;

import com.labplan.entities.generic.Entity;

/**
 * An interface which describes a Data Access Object (DAO) which implements the
 * Create, Read, Update and Delete (CRUD) functionality for a given entity.
 * 
 * <p>Unlike a {@link CrudInterface}, entities of a {@link DependentCrudInterface} have a parent {@link Entity}.
 * These entities cannot exist outside the context of their parent entity.</p>
 * 
 * @author Elian Doran
 *
 * @param <TParentEntity>	A type which represents the parent entity of the {@code TChildEntity}.
 * @param <TChildEntity>	A type which represents the actual entity, child of {@code TParentEntity}.
 * @param <TChildKey>		The type which represents the key of the {@code TChildEntity}.
 */
public interface DependentCrudInterface<TParentEntity extends Entity<?>, TChildEntity extends Entity<TChildKey>, TChildKey>
		extends CrudInterface<TChildEntity, TChildKey> {
	/**
	 * Obtains the parent entity of this entity.
	 * @return The parent entity of this entity.
	 */
	public TParentEntity getParentEntity();
}
