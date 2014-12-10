<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:url value="/login" var="loginUrl" />
<c:if test="${param.error != null}">
	<div class="errorblock">Invalid username and password.</div>
</c:if>
<c:if test="${param.logout != null}">
	<div class="messageblock">You have been logged out.</div>
</c:if>
<div class="loginForm">
	<form action="${loginUrl}" method="post">
		<p>
			<label for="username">Username</label> <input type="text"
				id="username" name="username" />
		</p>
		<p>
			<label for="password">Password</label> <input type="password"
				id="password" name="password" />
		</p>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<button type="submit" class="btn">Log in</button>
	</form>
</div>