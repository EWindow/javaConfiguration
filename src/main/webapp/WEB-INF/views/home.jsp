<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.priyank.sample.constant.ProjectConstant"%>
<c:set var="objectList" value="${employees}" />
<c:set var="status" value="${status}" />
<c:set var="message" value="${message}" />
<c:set var="pass" value="<%=ProjectConstant.UPLOAD_STATUS_PASS%>" />
<c:set var="fail" value="<%=ProjectConstant.UPLOAD_STATUS_FAIL%>" />
<c:set var="listSize" value="0" />
<c:if test="${status eq fail}">
	<div class="errorblock">${message}</div>
</c:if>
<c:if test="${status eq pass}">
	<div class="messageblock">${message}</div>
</c:if>
<div align="center">
	<form:form modelAttribute="employee" method="Post" action="crudUsers">

		<div class="gap10px"></div>
		<form:hidden path="id" />
		<input type="hidden" name="operation" id="operation" />
		<input type="hidden" name="created" id="created" />
		<div class="admin_form">
			<div class="admin_fields">
				<label for="firstname">First Name</label>
				<form:input path="firstname" cssClass="user_input" />
				<span class="error"><form:errors path="firstname" /></span>
			</div>
			<div class="admin_fields">
				<label for="lastname">Last Name</label>
				<form:input path="lastname" cssClass="user_input" />
				<span class="error"><form:errors path="lastname" /></span>
			</div>
			<div class="admin_fields">
				<label for="email">Email</label>
				<form:input path="email" cssClass="user_input" />
				<span class="error"><form:errors path="email" /></span>
			</div>
			<div class="admin_fields">
				<label for="telephone">telephone</label>
				<form:input path="telephone" cssClass="user_input" />
				<span class="error"><form:errors path="telephone" /></span>
			</div>
			<div class="clear_both_admin"></div>
		</div>

		<div class="gap10px"></div>
		<div class="gap10px"></div>
		<input type="button" name="clearAll" value="Clear All" id="clearAll"
			class="button1" />&nbsp;&nbsp;
			<input type="button" name="save" id="save" value="Save"
			class="button1" />&nbsp;&nbsp; <input type="button" class="button1"
			name="update" id="update" value="Update" />&nbsp;&nbsp; <input
			type="button" name="delete" class="button1" id="delete"
			value="Delete" />
	</form:form>
</div>
<div id="tableDataList" align="center">
	<table class="admin_data">
		<tr>
			<th style="width: 15px;">&nbsp;</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Email</th>
			<th>telephone</th>
			<th>Created Date</th>
		</tr>
		<c:choose>
			<c:when test="${objectList != null && objectList != '' && not empty objectList}">
				<c:forEach var="object" items="${objectList}" varStatus="myCounter">
					<c:set var="listSize" value="${listSize + 1 }" />
					<tr data-id="${object.id}">
						<td><input type="radio" name="radioName" class="radio"/></td>
						<td class="style_pref_firstName">${object.firstname}</td>
						<td class="style_pref_lastName">${object.lastname}</td>
						<td class="style_pref_emailId">${object.email}</td>
						<td class="style_pref_telephone">${object.telephone}</td>
						<td class="style_pref_created">${object.created}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<td align="center" colspan="6">No data Found</td>
			</c:otherwise>
		</c:choose>
	</table>
</div>

<script type="text/javascript">
$(document).ready(function(){
	clearAll();
});


$(".admin_data tr").removeClass("selected_row");

$(".admin_data tr").on("click", function() {
	$(".admin_data tr").removeClass("selected_row");	
	
	$("#id").val($(this).data("id"));
	$("#firstname").val($(this).find(".style_pref_firstName").text());
	$("#lastname").val($(this).find(".style_pref_lastName").text());
	$("#email").val($(this).find(".style_pref_emailId").text());
	$("#telephone").val($(this).find(".style_pref_telephone").text());
	$("#created").val($(this).find(".style_pref_created").text());
	
	$(this).find(".radio").prop("checked",true);
	$(this).addClass("selected_row");

	$("#save").hide();
	$("#update").show();
	$("#delete").show();
});

$("#save").on("click",function(){
	if (confirm("Do you want to save details?"))
	submitForm('<%=ProjectConstant.CRUD_CRAETE%>');
});
$("#update").on("click",function(){
	if (confirm("Do you want to update details?"))
		submitForm('<%=ProjectConstant.CRUD_UPDATE%>');
});
$("#delete").on("click",function(){
	if (confirm("Do you want to delete details?"))
		submitForm('<%=ProjectConstant.CRUD_DELETE%>');
	});
$("#clearAll").on("click", function() {
	clearAll();
});
function submitForm(operation) {
	$("#operation").val(operation);
	document.forms[0].submit();
}
function clearAll() {
	$(".admin_data tr").removeClass("selected_row");
	$(".admin_data tr").find("input[type='radio']").attr("checked", false);

	$("#id").val('');
	$("#firstname").val('');
	$("#lastname").val('');
	$("#email").val('');
	$("#telephone").val('');

	// 	$("#save").attr("disabled",false);
	// 	$("#update").attr("disabled",true);
	// 	$("#delete").attr("disabled",true);

	$("#save").show();
	$("#update").hide();
	$("#delete").hide();
}
</script>
</html>