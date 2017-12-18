package com.labplan.webapp;

public class PaginationUrlBuilder {
	private String baseUrl;
	private PageInformation pageInfo;
	private static final String disabledUrl = "";

	public PaginationUrlBuilder(String baseUrl, PageInformation pageInfo) {
		this.baseUrl = baseUrl;
		this.pageInfo = pageInfo;
	}
	
	public PaginationUrlBuilder(PageInformation pageInfo) {
		this("?page=", pageInfo);
	}
	
	public String getBaseUrl() {
		return baseUrl;
	}

	public PageInformation getPageInfo() {
		return pageInfo;
	}
	
	public String getFirstPageUrl() {
		if (pageInfo.getCurrent() == 1)
			return buildUrl(null);
		
		return buildUrl(1);
	}
	
	public String getPreviousPageUrl() {
		return buildUrl(pageInfo.getPreviousPage());
	}
	
	public String getNextPageUrl() {
		return buildUrl(pageInfo.getNextPage());
	}
	
	public String getLastPageUrl() {
		if (pageInfo.getCurrent() == pageInfo.getTotal())
			return buildUrl(null);
		
		return buildUrl(pageInfo.getTotal());
	}
	
	private String buildUrl(Integer page) {
		if (page == null)
			return disabledUrl;
			
		return baseUrl + page.toString();
	}
}
