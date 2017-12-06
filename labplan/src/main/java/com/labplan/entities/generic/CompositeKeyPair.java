package com.labplan.entities.generic;

/***
 * Represents a composite key identifying an {@link Entity} which is composed of two component keys of differing types.
 * This can be useful for representing composite database primary keys.
 * @author adoran
 *
 * @param <TFirst>	The type of the first component of the composite key.
 * @param <TSecond>	The type of the second component of the composite key.
 */
public class CompositeKeyPair<TFirst, TSecond> {
	TFirst firstKey;
	TSecond secondKey;
	
	public CompositeKeyPair() {
		
	}
	
	public CompositeKeyPair(TFirst firstKey, TSecond secondKey) {
		this.firstKey = firstKey;
		this.secondKey = secondKey;
	}

	public TFirst getFirstKey() {
		return firstKey;
	}
	
	public void setFirstKey(TFirst firstKey) {
		this.firstKey = firstKey;
	}
	
	public TSecond getSecondKey() {
		return secondKey;
	}
	
	public void setSecondKey(TSecond secondKey) {
		this.secondKey = secondKey;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof CompositeKeyPair))
			return false;

		return hashCode() == obj.hashCode();
	}
	
	@Override
	public int hashCode() {
		int hash = 17;
		hash = 31 * hash + firstKey.hashCode();
		hash = 31 * hash + secondKey.hashCode();
		return hash;
	}
}
