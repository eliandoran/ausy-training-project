package com.labplan.services;

import java.util.List;

import com.labplan.api.exceptions.EntityNotFoundException;
import com.labplan.entities.generic.Entity;
import com.labplan.persistence.generic.Dao;

public abstract class Service<TKey, TEntity extends Entity<TKey>, TDao extends Dao<TEntity, TKey>> {
	protected TDao dao;

	public Service(TDao dao) {
		this.dao = dao;
	}

	public abstract List<TEntity> getPage(int page, int entriesPerPage);

	public abstract Integer getPageCount(int entriesPerPage);

	/**
	 * Obtains the entity whose corresponding {@code key}/ID matches the given one.
	 * @param key	The key used to identify the entity to find.
	 * @return		The entity whose {@code key} matches the given one.
	 * @throws EntityNotFoundException	A {@link RuntimeException} if there is no entity corresponding to the given {@code key}.
	 */
	public abstract TEntity get(TKey key) throws EntityNotFoundException;
}
