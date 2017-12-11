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
 * @param <TParentEntity>	A type which represents the parent entity of the {@code TChildEntity}.
 * @param <TChildKey>		The type which represents the key of the {@code TChildEntity}.
 * @param <TChildEntity>	A type which represents the actual entity, child of {@code TParentEntity}.
 * @param <TChildDao>		A {@link DependentCrudInterface} representing the DAO for {@code TEntity}.
 */
public abstract class DependentCrudTester<TParentEntity extends Entity<?>, TChildKey, TChildEntity extends Entity<TChildKey>, TChildDao extends DependentCrudInterface<TParentEntity, TChildEntity, TChildKey>>
	extends CrudTester<TChildKey, TChildEntity, TChildDao> {
	
}
