package com.labplan.entities.generic;

/**
 * An <i>entity</i> represents a generic object holding information that is
 * usually obtained from/inserted into a data source. The entity can be
 * identified by a key of a generic type {@code TKey}.
 * 
 * <p>
 * In the context of a data source that is a database, each useful table could
 * be represented as distinct entities, while the key of the entity would
 * actually be the table's primary key.
 * </p>
 * 
 * @author Elian Doran
 *
 * @param <TKey>
 *            The type used by the key to identify this {@link Entity}.
 */
public abstract class Entity<TKey> {
	protected TKey id;

	/**
	 * Gets the key used for identifying this {@link Entity}.
	 * 
	 * @return The key used for identifying this {@link Entity}.
	 */
	public TKey getId() {
		return this.id;
	}

	/**
	 * Sets the key used for identifying this {@link Entity}.
	 * 
	 * @param id
	 *            The key to set.
	 */
	public void setId(TKey id) {
		this.id = id;
	}
}
