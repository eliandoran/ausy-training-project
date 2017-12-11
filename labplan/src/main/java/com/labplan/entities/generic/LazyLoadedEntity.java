package com.labplan.entities.generic;

import com.labplan.persistence.generic.Dao;

/**
 * A {@link LazyLoadedEntity} is a wrapper around a {@link Entity} that delays
 * the loading of the said entity until it is actually needed. This can be
 * useful when eager loading of an entity in a list could generate a large
 * amount of database traffic, without using a fraction of the loaded data.
 * 
 * <p>
 * In order for the lazy loading to work, the {@link LazyLoadedEntity} stores
 * the {@code Key} of the entity. When the entity is requested via
 * {@code getEntity()}, if it was not previously loaded the
 * {@link LazyLoadedEntity} will call the DAO stored in the {@code Dao} to
 * obtain the entity. Any future calls will obtain the cached entity.
 * </p>
 * 
 * @author Elian Doran
 *
 * @param <TKey>
 *            The type of the key used by {@code TKey}.
 * @param <TEntity>
 *            The {@link Entity} wrapped around this {@link LazyLoadedEntity}.
 */
public class LazyLoadedEntity<TKey, TEntity extends Entity<TKey>> {
	TEntity entity;
	Boolean isLoaded;
	Dao<TEntity, TKey> dao;
	TKey key;

	/**
	 * Create a new instance of {@link LazyLoadedEntity}. Its status is set to
	 * <i>not loaded</i>; {@code setEntity()}, {@code setKey()} and {@code setDao()}
	 * must be used to make the lazy loading work.
	 */
	public LazyLoadedEntity() {
		isLoaded = false;
	}

	/**
	 * Create a new instance of {@link LazyLoadedEntity} based around a
	 * 
	 * @param entity
	 */
	public LazyLoadedEntity(TEntity entity) {
		key = entity.getId();
		this.entity = entity;
		isLoaded = true;
	}

	/**
	 * Obtains the {@link Entity} wrapped in this {@link LazyLoadedEntity}. If the
	 * {@link Entity} has not yet been loaded, calling this method will determine
	 * the loading of the {@link Entity}. The loading is handled by using
	 * {@code getDao()} to obtain the appropriate DAO and then using it to read the
	 * {@link Entity} with the ID specified in {@code getKey()}. Future calls of
	 * this method will obtain the cached version of the {@link Entity}, so if
	 * modifications are made to the {@link Entity}, it will not update.
	 * 
	 * @return The entity wrapped in this {@link LazyLoadedEntity}
	 */
	public TEntity getEntity() {
		if (!isLoaded)
			load();

		return entity;
	}

	/**
	 * Sets the {@link Entity} wrapped in this {@link LazyLoadedEntity}. This is
	 * useful in case the {@link Entity} was already loaded prior to instantiating
	 * the {@link LazyLoadedEntity}.
	 * 
	 * @param entity
	 *            The {@link Entity} to be wrapped in this {@link LazyLoadedEntity}.
	 */
	public void setEntity(TEntity entity) {
		this.entity = entity;
	}

	/**
	 * Obtains the key that is used for identifying the {@link Entity}. It should be
	 * equal to {@code getEntity().getId()}. This will be used by
	 * {@code getEntity()} to load the {@link Entity} from the data source with the
	 * help of {@code getDao()}.
	 * 
	 * @return The key that is used for identifying the {@link Entity}.
	 */
	public TKey getKey() {
		return key;
	}

	/**
	 * Sets the key that is used for identifying the {@link Entity}. It should be
	 * equal to {@code getEntity().getId()}. This will be used by
	 * {@code getEntity()} to load the {@link Entity} from the data source with the
	 * help of {@code getDao()}.
	 * 
	 * @param key
	 *            The key that is used for identifying the {@link Entity}.
	 */
	public void setKey(TKey key) {
		this.key = key;
	}

	/**
	 * Indicates whether the {@link Entity} stored in {@code getEntity()} has been
	 * loaded from the data source.
	 * 
	 * @return {@code true} if the {@link Entity} stored in {@code getEntity()} has
	 *         been loaded from the data source, {@code false} otherwise.
	 */
	public Boolean getIsLoaded() {
		return isLoaded;
	}

	/**
	 * Obtains the {@link Dao} which will be used by {@code getEntity()} to load the
	 * {@link Entity} from the data source if it had not been loaded before.
	 * 
	 * @return The {@link Dao} which will be used by {@code getEntity()} to load the
	 *         {@link Entity}.
	 */
	public Dao<TEntity, TKey> getDao() {
		return dao;
	}

	/**
	 * Sets the {@link Dao} which will be used by {@code getEntity()} to load the
	 * {@link Entity} from the data source if it had not been loaded before.
	 * 
	 * @param dao
	 *            he {@link Dao} which will be used by {@code getEntity()} to load
	 *            the {@link Entity}.
	 */
	public void setDao(Dao<TEntity, TKey> dao) {
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
