<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="message" required="true" type="com.labplan.webapp.Message" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${not empty message}">
	<section class="message">
		${message.content}
	</section>
</c:if>