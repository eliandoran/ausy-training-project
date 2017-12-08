package com.labplan.entities;

import com.labplan.entities.generic.Entity;

/**
 * Represents a type of test a medical laboratory can offer for a {@link Patient}.
 * @author adoran
 *
 */
public class LabTest extends Entity<Integer> {
	private String name;
	private String description;
	private Float valueMin;
	private Float valueMax;

	/**
	 * Creates a new instance of a {@link LabTest}.
	 */
	public LabTest() {

	}

	/**
	 * Creates a new instance of a {@link LabTest} with predefined information.
	 * @param id			The numerical key of the {@link LabTest}.
	 * @param name			The name of this {@link LabTest}.
	 * @param description	A text describing the {@link LabTest}.
	 * @param valueMin		The minimum reference range of this {@link LabTest}.
	 * @param valueMax		The maximum reference range of this {@link LabTest}.
	 */
	public LabTest(Integer id, String name, String description, Float valueMin, Float valueMax) {
		this.setId(id);
		this.name = name;
		this.description = description;
		this.valueMin = valueMin;
		this.valueMax = valueMax;
	}

	/**
	 * Creates a new instance of a {@link LabTest} with predefined information.
	 * @param name			The name of this {@link LabTest}.
	 * @param description	A text describing the {@link LabTest}.
	 * @param valueMin		The minimum reference range of this {@link LabTest}.
	 * @param valueMax		The maximum reference range of this {@link LabTest}.
	 */
	public LabTest(String name, String description, Float valueMin, Float valueMax) {
		this(null, name, description, valueMin, valueMax);
	}

	/**
	 * Obtains the name of this {@link LabTest}.
	 * @return The name of this {@link LabTest}.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of this {@link LabTest}.
	 * @param name The name of this {@link LabTest}.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obtains the text describing this {@link LabTest}.
	 * @return The text describing this {@link LabTest}.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the text describing this {@link LabTest}.
	 * @param description The text describing this {@link LabTest}.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Obtains the minimum value of the <em>reference range</em> of this {@link LabTest}.
	 * 
	 * <p><em>The <strong>reference range</strong> is the normal range of values a {@link LabResult} can have.
	 * Should a {@link LabResult} have a {@code value} beyond the reference range, the {@link Patient} could
	 * have health issues.</em></p>
	 * @return The minimum value of the <em>reference range</em> of this {@link LabTest}.
	 */
	public Float getValueMin() {
		return valueMin;
	}

	/**
	 * Sets the minimum value of the <em>reference range</em> of this {@link LabTest}.
	 * 
	 * <p><em>The <strong>reference range</strong> is the normal range of values a {@link LabResult} can have.
	 * Should a {@link LabResult} have a {@code value} beyond the reference range, the {@link Patient} could
	 * have health issues.</em></p>
	 * @param valueMin The minimum value of the <em>reference range</em> of this {@link LabTest}.
	 */
	public void setValueMin(Float valueMin) {
		this.valueMin = valueMin;
	}

	/**
	 * Obtains the maximum value of the <em>reference range</em> of this {@link LabTest}.
	 * 
	 * <p><em>The <strong>reference range</strong> is the normal range of values a {@link LabResult} can have.
	 * Should a {@link LabResult} have a {@code value} beyond the reference range, the {@link Patient} could
	 * have health issues.</em></p>
	 * @return The maximum value of the <em>reference range</em> of this {@link LabTest}.
	 */
	public Float getValueMax() {
		return valueMax;
	}

	/**
	 * Sets the maximum value of the <em>reference range</em> of this {@link LabTest}.
	 * 
	 * <p><em>The <strong>reference range</strong> is the normal range of values a {@link LabResult} can have.
	 * Should a {@link LabResult} have a {@code value} beyond the reference range, the {@link Patient} could
	 * have health issues.</em></p>
	 * @param valueMin The maximum value of the <em>reference range</em> of this {@link LabTest}.
	 */
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
