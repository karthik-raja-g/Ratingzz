<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="adminHome.jsp"%>
<html>
  <head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="/css/displayplayerspage.css">
  </head>
<body>
        <table id="contentTable" class = "table" align="center" cellpadding = "10">
             <caption><h2 style = "color: white;" >List of Employees</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Reporting To</th>
                <th>Role</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach var="employee" items="${subordinates}">
                <tr>
                    <td class = "playertd"><c:out value="${employee.id}" /></td>
                    <td class = "playertd"><a href= view-employee?id=${employee.id}>
                       <c:out value="${employee.name}" /></a></td>
                    <td class = "playertd"><c:out value="${employee.role.name}" /></td>
                    <td class = "playertd" >
                      <button class = "deleteButton" onclick="location.href ='delete-employee?id=${employee.id}';">&#128465;</button></td>        
                     <td class = "playertd">
                      <button class = "editButton"
                          onclick="location.href ='edit-employee?id=${employee.id}';">&#x1F58B;
                      </button>   
                    </td>
                     <td class = "playertd">
                        <form action="get-employee-forms" method ="post">
                         <input type="hidden"  name = "employeeId" value="${employee.id}"><br>
                             <p><button>Show forms</button></p>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table> <br>
     </div>  
     </div>
</body>
</html>
