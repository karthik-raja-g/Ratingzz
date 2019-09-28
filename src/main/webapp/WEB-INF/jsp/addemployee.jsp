<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title> Create player </title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="resources/css/style.css">
    </head>
<body>
    <form:form action="create-employee" method="post" modelAttribute="employeeInfo">
        <div class="container">
                <div class="row">
                  	<div class="col-25">
                        <label for="name"> Name </label>
                  	</div>
              	    <div class="col-75">
                        <form:input type="text" path="name" required="required"/><br>
              	    </div>
                </div>
                <div class="row">
                  	<div class="col-25">
                        <label for="name"> Username </label>
                  	</div>
              	    <div class="col-75">
                        <form:input type="text" path="username" required="required"/><br>
              	    </div>
                </div>
                <div class="row">
                  	<div class="col-25">
                        <label for="name"> Email Id </label>
                  	</div>
              	    <div class="col-75">
                        <form:input  type="email" path="email" required="required"/><br>
              	    </div>
                </div>
                <div class="row">
               	<div class="col-25">
                        <label for="name"> Password </label>
                  	</div>
              	    <div class="col-75">
                        <form:input type="password" path="password" required="required"/><br>
              	    </div>
                </div>
                <div class="row">
                  	<div class="col-25">
                        <label for="name"> Role </label>
                  	</div>
              	    <div class="col-75">
                        <c:forEach var="role" items="${roles}">
                            <input type="radio" name="selectedRole" value="${role.id}" required />${role.name}
                        </c:forEach>
              	    </div>
                </div>
            </div>
            <div>
            <button type="submit" class="button button1">save</button>
            </div>
        </form:form>
    <center>
        <button onclick="location.href='view-employees';"> back </button>
    </center>
    </div>
</body>
</html>
