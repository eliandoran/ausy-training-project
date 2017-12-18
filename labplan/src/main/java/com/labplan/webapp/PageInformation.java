package com.labplan.webapp;

/**
 * Contains information regarding pagination, such as the <em>current page</em>, the <em>total number of pages<em> and the <em>number of entries per page</em>.
 * @author Elian Doran
 *
 */
public class PageInformation {
	private Integer current;
	private Integer total;
	private Integer entriesPerPage;
	
	/**
	 * Creates a new {@link PageInformation} with no data.
	 */
	public PageInformation() {
		
	}
	
	/**
	 * Creates a new {@link PageInformation} with the specified information.	
	 * @param current			The current page.
	 * @param total				The total number of pages.
	 * @param entriesPerPage	The maximum number of items per page.
	 */
	public PageInformation(Integer current, Integer total, Integer entriesPerPage) {
		this.current = current;
		this.total = total;
		this.entriesPerPage = entriesPerPage;
	}

	/**
	 * Sets the current page.
	 * @param current	The current page.
	 */
	public void setCurrent(Integer current) {
		this.current = current;
	}

	/**
	 * Sets the total number of pages.
	 * @param total		The total number of pages.
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * Sets the maximum number of items per page.
	 * @param entriesPerPage	The maximum number of items per page.
	 */
	public void setEntriesPerPage(Integer entriesPerPage) {
		this.entriesPerPage = entriesPerPage;
	}

	/**
	 * Gets the current page.
	 * @return The current page.
	 */
	public Integer getCurrent() {
		return current;
	}
	
	/**
	 * Gets the total number of pages.
	 * @return The total number of pages.
	 */
	public Integer getTotal() {
		return total;
	}
	
	/**
	 * Gets the maximum number of items per page.	
	 * @return The maximum number of items per page.
	 */
	public Integer getEntriesPerPage() {
		return entriesPerPage;
	}
	
	/**
	 * Gets the index of the first item on the current page. For example, if {@link PageInformation#current} is 2 and {@link PageInformation#entriesPerPage} is 4
	 * then the index of the first item on the second page is 5.
	 * @return The index of the first item on the current page.
	 */
	public Integer getStartIndex() {
		if (entriesPerPage == null || current == null)
			return null;
		
		return (entriesPerPage * (current - 1)) + 1;
	}
	
	/**
	 * Gets the index of the page previous to {@link PageInformation#current}, or {@code null} if there are no more pages.
	 * @return The page previous to {@link PageInformation#current}, or {@code null} if there are no more pages.
	 */
	public Integer getPreviousPage() {
		return (current > 1 ? current - 1 : null);
	}
	
	/**
	 * Gets the index of the page next to {@link PageInformation#current}, or {@code null} if there are no more pages.
	 * @return The page next to {@link PageInformation#current}, or {@code null} if there are no more pages.
	 */
	public Integer getNextPage() {
		return (current < total ? current + 1 : null);
	}
	
	/***
	 * Creates a new instance of a {@link PaginationUrlBuilder} with a reference to this {@link PageInformation}.
	 * @return A new {@link PaginationUrlBuilder} with a reference to this {@link PageInformation}.
	 */
	public PaginationUrlBuilder getPaginationUrlBuilder() {
		return new PaginationUrlBuilder(this);
	}
	
	/**
	 * Creates a new instance of a {@link PaginationUrlBuilder} with a reference to this {@link PageInformation} and with the given {@code baseUri}.
	 * @param baseUri The URI to prefix all the links with (i.e. "?page=").
	 * @return A new {@link PaginationUrlBuilder} with a reference to this {@link PageInformation}.
	 */
	public PaginationUrlBuilder getPaginationUrlBuilder(String baseUri) {
		return new PaginationUrlBuilder(baseUri, this);
	}
}
