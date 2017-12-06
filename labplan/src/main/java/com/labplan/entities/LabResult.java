package com.labplan.entities;

import java.math.BigDecimal;

import com.labplan.entities.generic.CompositeKeyPair;
import com.labplan.entities.generic.Entity;
import com.labplan.entities.generic.LazyLoadedEntity;

/**
 * Represents the result of a {@link LabTest} taken for a {@link Patient}.
 * @author adoran
 *
 */
public class LabResult
	extends Entity<CompositeKeyPair<
					LazyLoadedEntity<Integer, LabTest>,
					LazyLoadedEntity<Integer, LabList>>>
{
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
		
		LabResult compared = (LabResult)obj;
		
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
