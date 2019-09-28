<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<meta charset="utf-8"/>
<html>
    <link rel="stylesheet" href="resources/css/addplayerstoteam.css">
<head>
<link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.7.0/css/all.css' integrity='sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ' crossorigin='anonymous'>
    <title> Reporting person </title>
</head>
<body>
    <center>
        <h3> Add employee to reporting person </h3>
    </center>
        <button onclick="location.href='view-employees';" title="Back" style=" cursor:pointer; margin-left:3%; font-size:24px;" ><i class="fas fa-arrow-alt-circle-left"></i></button><br>
    
    <div align="center">
        <table border="1" cellpadding="5">
                    <tr><td>Id</td><td><c:out value="${employeeInfo.id}" /></td></tr>
                    <tr><td>Name</td><td><c:out value="${employeeInfo.name}" /></td></tr>
                    <tr><td>Employee Role</td><td><c:out value="${employeeInfo.role}" /></td></tr>
         </table>
    </div>
    <br>
    <div class="container">
            <c:forEach var="employee" items="${reportingPersons}"> 
            <form:form action="assign-reporting-person" method="post">
           <div class="card">
           <button type="submit" onclick="ConfirmDelete('${employeeInfo.name}', '${employee.name}')" style= "cursor:pointer; height:230px; width:260px">
             <div align="center">
             <input type="hidden" name="id" value="${employeeInfo.id}"/>
             <input type="hidden" name="reportingPersonId" value="${employee.id}">
             <br>
                  <table>
                    <tr><td> Employee id   </td>
                    <td> <c:out value="${employee.id}" /></td> </tr>
                    <tr><td>  Name  </td>
                    <td> <c:out value="${employee.name}" /> </td></tr>
                    <tr><td>  Employee Role  </td>
                    <td> <c:out value="${employee.role}" /> </td></tr>
                  </table>
                </div>
            </button> 
             </div>
             </form:form>
            </c:forEach>
    </div>
  <script>
    function ConfirmDelete(employeeName,reportingPersonName)
    {
      var x = confirm("Are you sure you want to assign "+employeeName+" to "+reportingPersonName+" ?");
          if (x) {
              return true;
          } else {
            return false;
          }
    }
  </script>
</body>
</html>
