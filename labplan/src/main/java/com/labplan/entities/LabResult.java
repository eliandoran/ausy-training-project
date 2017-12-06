package com.labplan.entities;

public class LabResult extends Entity<CompositeKeyPair<LabTest, LabList>> {
	private LabList list;
	
	private Float value;

	public LabResult() {
		
	}

	public LabResult(Integer id, LabList list, Float value) {
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
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LabResult))
			return false;
		
		return hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + getId().hashCode();
		hash = 31 * hash + value.hashCode();
		return hash;
	}
	
}
