<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="adminHome.jsp"%>
<html>
<head>
    <link rel="stylesheet" href="resources/css/style.css">
    <title> view Employee </title>
</head>
<body>   
                <div class="container">
                    <div class="row">
                      	<div class="col-25">
                            <label for="name"> ID </label>
                      	</div>
                  	    <div class="col-75">
                            <input type="text"  value="${employeeInfo.id}" readonly><br>
                  	    </div>
                    </div>
                    <div class="row">
                      	<div class="col-25">
                            <label for="name"> Name </label>
                      	</div>
                  	    <div class="col-75">
                            <input type="text"  value="${employeeInfo.name}" readonly><br>
                  	    </div>
                    </div>
                    <div class="row">
                      	<div class="col-25">
                            <label for="name"> Username </label>
                      	</div>
                  	    <div class="col-75">
                            <input type="text"  value="${employeeInfo.username}" readonly><br>
                  	    </div>
                    </div>
                    <div class="row">
                      	<div class="col-25">
                            <label for="name"> Email Id </label>
                      	</div>
                  	    <div class="col-75">
                            <input type="text"  value="${employeeInfo.email}" readonly><br>
                  	    </div>
                    </div>
                    <div class="row">
                      	<div class="col-25">
                            <label for="name"> Role </label>
                      	</div>
                  	    <div class="col-75">
                            <input type="text"  value="${employeeInfo.role}" readonly><br>
                  	    </div>
                    </div>
                    <div class="row">
                      	<div class="col-25">
                            <label for="name"> Reporting person </label>
                      	</div>
                  	    <div class="col-75">
                            <input type="text"  value="${employeeInfo.reportingPerson}" readonly><br>
                  	    </div>
                    </div>
                    <div class="row">
                      	<div class="col-25">
                            <label for="name"> Created Date </label>
                      	</div>
                  	    <div class="col-75">
                            <input type="text"  value="${employeeInfo.createdDate}" readonly><br>
                  	    </div>
                    </div>
                    <div class="row">
                      	<div class="col-25">
                            <label for="name"> Modified Date </label>
                      	</div>
                  	    <div class="col-75">
                            <input type="text"  value="${employeeInfo.modifiedDate}" readonly><br>
                  	    </div>
                    </div>
            </div>
            <div>
            <button onclick="location.href='edit-employee?id=${employeeInfo.id}';" class="button button1">Edit</button>
            &nbsp;&nbsp;
            <button onclick="location.href='delete-employee?id=${employeeInfo.id}';" class="button button1" title="Click to delete player" >Delete</button>
            </div>
            <center>
        <br>
        <button onclick="location.href='view-employees';" class="button button1" > back </button>
        </center>
</body>
</html>
