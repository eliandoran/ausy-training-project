package com.labplan.entities.generic;

import com.labplan.persistence.generic.CrudInterface;

/**
 * A {@link LazyLoadedEntity} is a wrapper around a {@link Entity} that delays the loading of the said entity until it is actually needed.
 * This can be useful when eager loading of an entity in a list could generate a large amount of database traffic, without using a fraction of the loaded data.
 * 
 * <p>In order for the lazy loading to work, the {@link LazyLoadedEntity} stores the {@code Key} of the entity. When the entity is requested
 * via {@code getEntity()}, if it was not previously loaded the {@link LazyLoadedEntity} will call the DAO stored in the {@code Dao} to
 * obtain the entity. Any future calls will obtain the cached entity.</p>
 * @author Elian Doran
 *
 * @param <TKey>		The type of the key used by {@code TKey}.
 * @param <TEntity>		The {@link Entity} wrapped around this {@link LazyLoadedEntity}.
 */
public class LazyLoadedEntity<TKey, TEntity extends Entity<TKey>> {
	TEntity entity;
	Boolean isLoaded;
	CrudInterface<TEntity, TKey> dao;
	TKey key;

	/**
	 * Create a new instance of {@link LazyLoadedEntity}. Its status is set to <i>not loaded</i>;
	 * {@code setEntity()}, {@code setKey()} and {@code setDao()} must be used to make the lazy loading work.
	 */
	public LazyLoadedEntity() {
		isLoaded = false;
	}
	
	/**
	 * Create a new instance of {@link LazyLoadedEntity} based around a 
	 * @param entity
	 */
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

	private void load() {
		if (dao == null)
			throw new NullPointerException("DAO used for lazy loading is null.");
		
		entity = dao.read(key);
		isLoaded = true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LazyLoadedEntity))
			return false;
		
		return hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode() {
		return (key != null ? key.hashCode() : 0);
	}
	
	@Override
	public String toString() {
		String entityName = (entity != null ? entity.getClass().getName() : "null");
		String keyName = (key != null ? key.toString() : "null");
		
		return String.format("<LazyLoadedEntity %s, ID: %s>", entityName, keyName);
	}
}
