package com.labplan.services;

import java.util.List;

import com.labplan.entities.generic.Entity;
import com.labplan.persistence.generic.Dao;

public abstract class Service<TEntity extends Entity<?>, TDao extends Dao<TEntity, ?>> {
	TDao dao;
	
	public Service(TDao dao) {
		this.dao = dao;
	}
	
	public abstract List<TEntity> getPage(int page, int entriesPerPage);
}
