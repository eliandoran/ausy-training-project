<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="pageInfo" required="true" type="com.labplan.webapp.PageInformation" %>
<%@ attribute name="colspan" required="true" type="java.lang.Integer" %>
<%@ attribute name="baseUrl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty baseUrl}">
	<c:set var="baseUrl" value="?page=" />
</c:if>

<c:set var="pagination" value="${pageInfo.getPaginationUrlBuilder(baseUrl)}" />

<tr class="pagination">
	<td colspan="${colspan}">
		<table>
			<tr>
				<td><a href="${pagination.firstPageUrl}"><img src="<c:url value="/static/assets/pagination-first.png" />" alt="First" title="Go to the first page" /></a></td>
				<td><a href="${pagination.previousPageUrl}"><img src="<c:url value="/static/assets/pagination-previous.png" />" alt="Previous" title="Go to the previous page" /></a></td>
				
				<td class="page-indicator">Page ${page.current} out of ${page.total}</td>
				
				<td><a href="${pagination.nextPageUrl}"><img src="<c:url value="/static/assets/pagination-next.png" />" alt="Next" title="Go to the next page" /></a></td>
				<td><a href="${pagination.lastPageUrl}"><img src="<c:url value="/static/assets/pagination-last.png" />" alt="Last" title="Go to the last page" /></a></td>
			</tr>
		</table>
	</td>
</tr>