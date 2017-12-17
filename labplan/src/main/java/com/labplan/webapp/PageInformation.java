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
}
