package com.labplan.entities;

public class LabResult extends Entity<Integer> {
	private LabList list;
	
	private Float value;

	public LabResult() {

	}

	public LabResult(Integer id, LabList list, Float value) {
		this.id = id;
		this.list = list;
		this.value = value;
	}

	public LabResult(Float value) {
		this(null, null, value);
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	public LabList getList() {
		return list;
	}

	public void setList(LabList list) {
		this.list = list;
	}
}
