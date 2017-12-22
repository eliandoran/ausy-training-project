<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ attribute name="title" %>
<%@ attribute name="pageInfo" type="com.labplan.webapp.PageInformation" %>

<c:if test="${not empty title}">
	${title}
</c:if>

<c:if test="${(not empty pageInfo) and (pageInfo.current gt 0)}">
	(${pageInfo.current}/${pageInfo.total})
</c:if>

<c:if test="${not empty title}">&rsaquo;</c:if>

LabPlan