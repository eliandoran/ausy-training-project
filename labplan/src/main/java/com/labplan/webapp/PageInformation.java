package com.labplan.webapp;

public class PageInformation {
	private Integer current;
	private Integer total;
	private Integer entriesPerPage;
	
	public PageInformation() {
		
	}
	
	public PageInformation(Integer current, Integer total, Integer entriesPerPage) {
		this.current = current;
		this.total = total;
		this.entriesPerPage = entriesPerPage;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public void setEntriesPerPage(Integer entriesPerPage) {
		this.entriesPerPage = entriesPerPage;
	}

	public Integer getCurrent() {
		return current;
	}
	
	public Integer getTotal() {
		return total;
	}
	
	public Integer getEntriesPerPage() {
		return entriesPerPage;
	}
	
	public Integer getStartIndex() {
		if (entriesPerPage == null || current == null)
			return null;
		
		return (entriesPerPage * (current - 1)) + 1;
	}
	
	public Integer getPreviousPage() {
		return (current > 1 ? current - 1 : null);
	}
	
	public Integer getNextPage() {
		return (current < total ? current + 1 : null);
	}
	
	public PaginationUrlBuilder getPaginationUrlBuilder() {
		return new PaginationUrlBuilder(this);
	}
	
	public PaginationUrlBuilder getPaginationUrlBuilder(String baseUri) {
		return new PaginationUrlBuilder(baseUri, this);
	}
}
