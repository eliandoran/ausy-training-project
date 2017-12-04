package com.labplan.entities;

public class LabTest extends Entity<Integer> {
	private String name;
	private String description;
	private Float valueMin;
	private Float valueMax;
	
	public LabTest() {
		
	}
	
	public LabTest(Integer id, String name, String description, Float valueMin, Float valueMax) {
		this.setId(id);
		this.name = name;
		this.description = description;
		this.valueMin = valueMin;
		this.valueMax = valueMax;
	}
	
	public LabTest(String name, String description, Float valueMin, Float valueMax) {
		this(null, name, description, valueMin, valueMax);
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Float getValueMin() {
		return valueMin;
	}
	
	public void setValueMin(Float valueMin) {
		this.valueMin = valueMin;
	}
	
	public Float getValueMax() {
		return valueMax;
	}
	
	public void setValueMax(Float valueMax) {
		this.valueMax = valueMax;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LabTest))
			return false;
		
		return hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + id.hashCode();
		hash = 31 * hash + name.hashCode();
		hash = 31 * hash + description.hashCode();
		hash = 31 * hash + valueMin.hashCode();
		hash = 31 * hash + valueMax.hashCode();
		return hash;
	}
}
