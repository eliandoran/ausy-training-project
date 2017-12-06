package com.labplan.entities;

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
}
