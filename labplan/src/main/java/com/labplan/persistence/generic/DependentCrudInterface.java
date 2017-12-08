package com.labplan.persistence.generic;

import com.labplan.entities.generic.Entity;

public interface DependentCrudInterface<TParentEntity extends Entity<?>, TChildEntity extends Entity<TChildKey>, TChildKey>
		extends CrudInterface<TChildEntity, TChildKey> {

	public TParentEntity getParentEntity();

	public void setParentEntity(TParentEntity parentEntity);
}
