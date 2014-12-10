<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:url value="/logout" var="logoutUrl" />
<div id="header">
	<div class="container">
		<h1>Java Configurations</h1>
		<sec:authorize access="hasRole('ROLE_USER')" >
			<div class="button_logout">
				<a href='${logoutUrl}'> Logout</a>
			</div>
			<div class="userInfo">you are logged in as <b><sec:authentication property="principal.username"/></b>
			</div>
		</sec:authorize>
	</div>
</div>
