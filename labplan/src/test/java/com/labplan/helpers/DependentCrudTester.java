package com.labplan.helpers;

import com.labplan.entities.generic.Entity;
import com.labplan.persistence.generic.DependentCrudInterface;

/**
 * This is an abstract test suite which implements a set of predefined test
 * cases for testing the CRUD capabilities of a {@link DependentCrudInterface}
 * implementation.
 * 
 * @author Elian Doran
 *
 * @param <TParent>	A type which represents the parent entity of the {@code TEntity}.
 * @param <TKey>	The type which represents the key of the {@code TEntity}.
 * @param <TEntity>	A type which represents the actual entity, child of {@code TParent}.
 * @param <TDao>	A {@link DependentCrudInterface} representing the DAO for {@code TEntity}.
 */
public abstract class DependentCrudTester<TParent extends Entity<?>, TKey, TEntity extends Entity<TKey>, TDao extends DependentCrudInterface<TParent, TEntity, TKey>>
	extends CrudTester<TKey, TEntity, TDao> {
	
}
