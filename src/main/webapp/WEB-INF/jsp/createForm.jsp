<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ include file="adminHome.jsp"%>
<html>
<head>
<title> Create player </title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/css/createForm.css">
</head>
<body>
<div class= "create">
<br>
<center><h1>New Form</h1></center>
<form:form action="add-form" method="post" modelAttribute="form">
<table style="margin:auto;">
<tr><td>Form Title:</td><td><form:input type="text" path="title" required="required" /></td></tr>
<tr><td>Roles:</td><td>  
<c:forEach var="role" items="${roles}">
<input type="checkbox" name="selectedRoles" value="${role.id}"/>${role.name}
</c:forEach>
</tr>
<tr><td>Form Description : </td><td> <form:input type="text" path="description" /></td></tr>
<tr><center><td colspan="2"><button type="submit"/> Submit </button></center></td>
</table>  
</form:form>
</div>
<center>
    <button onclick="location.href='index.jsp;"> back </button>
</center>
</div>
</body>
</html> 
