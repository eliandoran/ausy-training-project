package com.labplan.helpers;import com.labplan.entities.generic.Entity;
import com.labplan.persistence.generic.CrudInterface;
import com.labplan.persistence.generic.DependentCrudInterface;

public abstract class DependentCrudTester<TParent extends Entity<?>, TKey, TEntity extends Entity<TKey>, TDao extends DependentCrudInterface<TParent, TEntity, TKey>>
	extends CrudTester<TKey, TEntity, TDao> {
	
}
