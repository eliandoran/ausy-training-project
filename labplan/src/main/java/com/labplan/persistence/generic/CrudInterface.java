package com.labplan.persistence.generic;

import java.util.Set;

public interface CrudInterface<TEntity, TKey> {
	public TEntity read(TKey key);
	public Set<TEntity> readAll();
	public TKey create(TEntity entity);
	public boolean update(TEntity entity);
	public boolean delete(TEntity entity);
}
