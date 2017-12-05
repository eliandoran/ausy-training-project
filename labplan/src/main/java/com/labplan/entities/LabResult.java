package com.labplan.entities;

public class LabResult extends Entity<Integer> {
	private Integer testId;
	private Integer listId;
	private Float value;

	public LabResult() {

	}

	public LabResult(Integer testId, Integer listId, Float value) {
		this.testId = testId;
		this.listId = listId;
		this.value = value;
	}

	public LabResult(Float value) {
		this(null, null, value);
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public Integer getListId() {
		return listId;
	}

	public void setListId(Integer listId) {
		this.listId = listId;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
}
