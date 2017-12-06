package com.labplan.entities.generic;

public abstract class Entity<TKey> {
	protected TKey id;

	public TKey getId() {
		return this.id;
	}

	public void setId(TKey id) {
		this.id = id;
	}
}
