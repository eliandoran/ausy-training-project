package com.labplan.entities.generic;

import com.labplan.entities.LabList;
import com.labplan.persistence.generic.CrudInterface;

public class LazyLoadedEntity<TKey, TEntity extends Entity<TKey>> {
	TEntity entity;
	Boolean isLoaded;
	CrudInterface<TEntity, TKey> dao;
	TKey key;

	public LazyLoadedEntity() {
		
	}
	
	public LazyLoadedEntity(TEntity entity) {
		key = entity.getId();
		this.entity = entity;
		isLoaded = true;
	}
	
	public TEntity getEntity() {
		if (!isLoaded)
			load();
		
		return entity;
	}

	public void setEntity(TEntity entity) {
		this.entity = entity;
	}
	
	public TKey getKey() {
		return key;
	}

	public void setKey(TKey key) {
		this.key = key;
	}
	
	public Boolean getIsLoaded() {
		return isLoaded;
	}

	public CrudInterface<TEntity, TKey> getDao() {
		return dao;
	}
	
	public void setDao(CrudInterface<TEntity, TKey> dao) {
		this.dao = dao;
	}

	void load() {
		entity = dao.read(key);
		isLoaded = true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LabList))
			return false;
		
		return hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode() {
		return key.hashCode();
	}
}