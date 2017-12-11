package com.labplan.entities;

import java.math.BigDecimal;

import com.labplan.entities.generic.Entity;
import com.labplan.entities.generic.LazyLoadedEntity;

/**
 * Represents the result of a {@link LabTest} taken for a {@link Patient}.
 * 
 * @author Elian Doran
 *
 */
public class LabResult extends Entity<LazyLoadedEntity<Integer, LabTest>> {
	private Float value;

	/**
	 * Creates a new instance of {@link LabResult}.
	 */
	public LabResult() {

	}

	/**
	 * Gets the numerical value of this {@link LabResult}. This value can be
	 * compared with the {@code minimumValue} and {@code maximumValue} of the
	 * corresponding {@link LabTest} to see whether the {@link Patient} is within
	 * normal ranges.
	 * 
	 * @return The numerical value of this {@link LabResult}.
	 */
	public Float getValue() {
		return value;
	}

	/**
	 * Sets the numerical value of this {@link LabResult}.
	 * 
	 * @param value
	 *            The numerical value of this {@link LabResult}.
	 */
	public void setValue(Float value) {
		this.value = value;
	}

	/**
	 * Obtains the corresponding {@link LabTest} of this {@link LabResult}.
	 * 
	 * <p>
	 * <em>Note that this is a short-hand for {@code getId().getEntity()} with
	 * null-checking in place.</em>
	 * </p>
	 * 
	 * @return The corresponding {@link LabTest} of this {@link LabResult}, or
	 *         {@code null} otherwise.
	 */
	public LabTest getTest() {
		if (getId() == null || getId().getEntity() == null)
			return null;

		return getId().getEntity();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof LabResult))
			return false;

		LabResult compared = (LabResult) obj;

		if (!compared.getId().equals(getId()))
			return false;

		if (Math.abs(compared.getValue() - getValue()) > 0.05)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + getId().hashCode();
		hash = 31 * hash + new Float(round(value, 4)).hashCode();
		return hash;
	}

	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
}
