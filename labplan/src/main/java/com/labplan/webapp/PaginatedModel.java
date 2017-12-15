package com.labplan.webapp;

public class PaginatedModel {
	private Integer currentPage;
	private Integer totalPages;
	
	/**
	 * @return the currentPage
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}
	
	/**
	 * @param currentPage the currentPage to set
	 */
	protected void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * @return the totalPages
	 */
	public Integer getTotalPages() {
		return totalPages;
	}

	/**
	 * @param totalPages the totalPages to set
	 */
	protected void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
}
