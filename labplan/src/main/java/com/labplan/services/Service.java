package com.labplan.services;

import java.util.List;

import com.labplan.entities.generic.Entity;
import com.labplan.persistence.generic.Dao;

public abstract class Service<TKey, TEntity extends Entity<TKey>, TDao extends Dao<TEntity, TKey>> {
	protected TDao dao;

	public Service(TDao dao) {
		this.dao = dao;
	}

	public abstract List<TEntity> getPage(int page, int entriesPerPage);

	public abstract Integer getPageCount(int entriesPerPage);
	
	public abstract TEntity get(TKey key);
}
