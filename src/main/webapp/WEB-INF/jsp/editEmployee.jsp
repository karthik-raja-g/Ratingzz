<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <link rel="stylesheet" href="resources/css/style.css">
    <title> view Employee </title>
</head>
<body> 
        <form:form action="update-employee" modelAttribute="employeeInfo" method="post">
            <form:input type="hidden" path="id" value="${employeeInfo.id}"/>
                <div class="container">
                    <div class="row">
                      	<div class="col-25">
                            <label for="name"> Name </label>
                      	</div>
                  	    <div class="col-75">
                            <form:input type="text" path="name"  value="${employeeInfo.name}"/><br>
                  	    </div>
                    </div>
                    <div class="row">
                      	<div class="col-25">
                            <label for="name"> Username </label>
                      	</div>
                  	    <div class="col-75">
                            <form:input type="text" path="username" value="${employeeInfo.username}"/><br>
                  	    </div>
                    </div>
                    <div class="row">
                      	<div class="col-25">
                            <label for="name"> Email Id </label>
                      	</div>
                  	    <div class="col-75">
                            <form:input type="text" path="email" value="${employeeInfo.email}"/><br>
                  	    </div>
                    </div>
            </div>
            <div>
            <button class="button button1"> Update </button>
            </form:form>
            &nbsp;&nbsp;
            <button onclick="location.href='view-employees';" class="button button1" > Cancel </button>
            </div>
</body>
</html>
