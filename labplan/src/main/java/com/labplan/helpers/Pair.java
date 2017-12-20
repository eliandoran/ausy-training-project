package com.labplan.helpers;

public class Pair<TFirst, TSecond> {
	private TFirst first;
	private TSecond second;
	
	public Pair() {
		
	}
	
	public Pair(TFirst first, TSecond second) {
		this.setFirst(first);
		this.setSecond(second);
	}

	/**
	 * @return the first
	 */
	public TFirst getFirst() {
		return first;
	}

	/**
	 * @param first the first to set
	 */
	public void setFirst(TFirst first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public TSecond getSecond() {
		return second;
	}

	/**
	 * @param second the second to set
	 */
	public void setSecond(TSecond second) {
		this.second = second;
	}
}
