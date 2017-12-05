package com.labplan.entities;

public abstract class Entity<TKey> {
	protected TKey id;

	public TKey getId() {
		return this.id;
	}

	public void setId(TKey id) {
		this.id = id;
	}
}
