package com.labplan.helpers;

import com.labplan.entities.generic.Entity;
import com.labplan.persistence.generic.ChildDao;

/**
 * This is an abstract test suite which implements a set of predefined test
 * cases for testing the CRUD capabilities of a {@link ChildDao}
 * implementation.
 * 
 * @author Elian Doran
 *
 * @param <TParentEntity>	A type which represents the parent entity of the {@code TChildEntity}.
 * @param <TChildKey>		The type which represents the key of the {@code TChildEntity}.
 * @param <TChildEntity>	A type which represents the actual entity, child of {@code TParentEntity}.
 * @param <TChildDao>		A {@link ChildDao} representing the DAO for {@code TEntity}.
 */
public abstract class ChildDaoTester<TParentEntity extends Entity<?>, TChildKey, TChildEntity extends Entity<TChildKey>, TChildDao extends ChildDao<TParentEntity, TChildEntity, TChildKey>>
	extends DaoTester<TChildKey, TChildEntity, TChildDao> {
	
}
