package com.labplan.helpers;

/**
 * A pair represents a tuple of exactly two elements. 
 * @author Elian Doran
 *
 * @param <TFirst>		The type of the first element of the pair.
 * @param <TSecond>		The type of the second element of the pair.
 */
public class Pair<TFirst, TSecond> {
	private TFirst first;
	private TSecond second;
	
	/**
	 * Creates a new empty pair.
	 */
	public Pair() { }
	
	/**
	 * Creates a pair with the given elements.
	 * @param first		The first element of the pair.
	 * @param second	The second element of the pair.
	 */
	public Pair(TFirst first, TSecond second) {
		this.setFirst(first);
		this.setSecond(second);
	}

	/**
	 * @return The first element of the pair.
	 */
	public TFirst getFirst() {
		return first;
	}

	/**
	 * @param first The first element of the pair.
	 */
	public void setFirst(TFirst first) {
		this.first = first;
	}

	/**
	 * @return The second element of the pair.
	 */
	public TSecond getSecond() {
		return second;
	}

	/**
	 * @param The second element of the pair.
	 */
	public void setSecond(TSecond second) {
		this.second = second;
	}
}
