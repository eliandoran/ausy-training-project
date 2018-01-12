<%@ tag language="java" pageEncoding="ISO-8859-1" %>
<%@ attribute name="text" required="true" %>
<%@ attribute name="maxLength" required="false" type="java.lang.Integer" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:if test="${empty maxLength}">
	<c:set var="maxLength" value="110" />
</c:if>

<div class="excerpt">
	<c:choose>
		<c:when test="${fn:length(text) gt maxLength}">
			<c:set var="excerpt" value="${fn:substring(text, 0, maxLength)} [...]" />
			<details>
				<summary>${excerpt}</summary>
				<span>${text}</span>
			</details>	
		</c:when>
		
		<c:otherwise>
			${text}
		</c:otherwise>
	</c:choose>
</div>