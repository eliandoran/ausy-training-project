package com.labplan.webapp;

/**
 * Provides a set of methods for building URLs for pagination buttons (i.e.
 * <em>go the first page</em>, <em>go to the next page</em>). Page information
 * is obtained from a {@link PageInformation} object.
 * 
 * @author Elian Doran
 *
 */
public class PaginationUrlBuilder {
	private String baseUrl;
	private PageInformation pageInfo;
	private static final String disabledUrl = "#";

	/**
	 * Creates a new {@link PaginationUrlBuilder}.
	 * 
	 * @param baseUrl
	 *            The URI to prefix all the links with (i.e. "?page=").
	 * @param pageInfo
	 *            The {@link PageInformation} to obtain information from.
	 */
	public PaginationUrlBuilder(String baseUrl, PageInformation pageInfo) {
		this.baseUrl = baseUrl;
		this.pageInfo = pageInfo;
	}

	/**
	 * Creates a new {@link PaginationUrlBuilder}.
	 * 
	 * @param pageInfo
	 *            The {@link PageInformation} to obtain information from.
	 */
	public PaginationUrlBuilder(PageInformation pageInfo) {
		this("?page=", pageInfo);
	}

	/**
	 * Gets the URI to prefix all the links with (i.e. "?page=").
	 * 
	 * @return The URI to prefix all the links with.
	 */
	public String getBaseUrl() {
		return baseUrl;
	}

	/**
	 * Gets the {@link PageInformation} used to obtain information from.
	 * 
	 * @return The corresponding {@link PageInformation}.
	 */
	public PageInformation getPageInfo() {
		return pageInfo;
	}

	/**
	 * Gets a {@link String} consisting of the {@link #baseUrl} and the index to the
	 * first page. If {@link PageInformation#getCurrent()} is already the first
	 * page, {@link PaginationUrlBuilder#disabledUrl} is returned.
	 * 
	 * @return A link to the first page, or {@link PaginationUrlBuilder#disabledUrl}
	 *         if already there.
	 */
	public String getFirstPageUrl() {
		if (pageInfo.getCurrent() == 1)
			return buildUrl(null);

		return buildUrl(1);
	}

	/**
	 * Gets a {@link String} consisting of the {@link #baseUrl} and the index to the
	 * page previous to the current one. If any, returns
	 * {@link PageInformation#getCurrent()}{@code -1},
	 * {@link PaginationUrlBuilder#disabledUrl} otherwise.
	 * 
	 * @return A link to the previous page, or
	 *         {@link PaginationUrlBuilder#disabledUrl} if no more pages.
	 */
	public String getPreviousPageUrl() {
		return buildUrl(pageInfo.getPreviousPage());
	}

	/**
	 * Gets a {@link String} consisting of the {@link #baseUrl} and the index to the
	 * page next to the current one. If any, returns
	 * {@link PageInformation#getCurrent()}{@code +1},
	 * {@link PaginationUrlBuilder#disabledUrl} otherwise.
	 * 
	 * @return A link to the next page, or {@link PaginationUrlBuilder#disabledUrl}
	 *         if no more pages.
	 */
	public String getNextPageUrl() {
		return buildUrl(pageInfo.getNextPage());
	}

	/**
	 * Gets a {@link String} consisting of the {@link #baseUrl} and the index to the
	 * last page. If {@link PageInformation#getCurrent()} is already the last page,
	 * {@link PaginationUrlBuilder#disabledUrl} is returned.
	 * 
	 * @return A link to the last page, or {@link PaginationUrlBuilder#disabledUrl}
	 *         if already there.
	 */
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
