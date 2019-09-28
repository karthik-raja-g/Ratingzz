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
        <c:if test="${role == 'ROLE_ADMIN'}">
        <div>
            <button class = "deleteButton" onclick="location.href ='new-employee';">Add New Employee</button>
        </div> 
        </c:if>
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
            <c:forEach var="employee" items="${employeePaginationInfo.employees}">
                <tr>
                    <td class = "playertd"><c:out value="${employee.id}" /></td>
                    <td class = "playertd"><a href= view-employee?id=${employee.id}>
                       <c:out value="${employee.name}" /></a></td>
                    <td class = "playertd"><c:out value="${employee.reportingPerson}" /></td>
                    <td class = "playertd"><c:out value="${employee.role}" /></td>
                    <td class = "playertd" > 
                      <button class = "deleteButton" onclick="location.href ='delete-employee?id=${employee.id}';">&#128465;</button></td>        
                     <td class = "playertd">
                      <button class = "editButton" 
                          onclick="location.href ='edit-employee?id=${employee.id}';">&#x1F58B;
                      </button>   
                    </td>
                </tr>
            </c:forEach>
        </table> <br>
     </div>  
     </div>  
        <input type="hidden"  value="${currentPage}" id="currentPage" />
        <div style="float:left;">
        <c:if test="${currentPage  gt 0} ">
            <button onclick="callAjax(${currentPage},1);">Previous</button>
        </c:if>
    </div>
    <center>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${employeePaginationInfo.noOfPages}" var="page">
                <c:choose>
                    <c:when test="${currentPage eq page}">
                        <td><button onclick="callAjax(${page},0);">${page}</button></td>
                    </c:when>
                    <c:otherwise>
                        <td><button onclick="callAjax(${page},0);">${page}</button></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    </center>
    <div style="float:right;">
    <c:if test="${currentPage lt employeePaginationInfo.noOfPages}">
        <button onclick="callAjax(${currentPage},2);">Next</button>
    </c:if>
    </div>
    </div>
<script>
function callAjax(page, direction) {
httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        console.log('Unable to create XMLHTTP instance');
        return false;
    }
    if (0 == direction) {
        document.getElementById('currentPage').value=page;
        currentPage = page;
    } else if (1 == direction){
        currentPage = document.getElementById('currentPage').value;
        currentPage = currentPage - 1;
        document.getElementById('currentPage').value=currentPage;
    } else if (2 == direction){
        currentPage = document.getElementById('currentPage').value;
        currentPage = currentPage * 1 + 1;
        document.getElementById('currentPage').value=currentPage;
    }
    httpRequest.open('GET', 'view-employees-page-wise?page='+currentPage);
    httpRequest.responseType = 'json';
    httpRequest.send();
    httpRequest.onreadystatechange = function() {
    if (httpRequest.readyState === XMLHttpRequest.DONE) {
        if (httpRequest.status === 200) {
            var j =0;
            var array = httpRequest.response;                    
            for (var i=1; i<= array.length; i++) {
                var row = document.getElementById('contentTable').rows;
                var column = row[i].cells;
                var name = array[j].name;
                var id = array[j].id;
                var view = name.link("view-employee?id=" + id);
                column[0].innerHTML = id;
                column[1].innerHTML = view;
                column[2].innerHTML = array[j].reportingPerson;
                column[3].innerHTML = array[j].role;
                column[4].style.display ='';
                column[5].style.display ='';
                var deleteBtn = document.createElement("BUTTON");
                deleteBtn.id ='dbtn';
                deleteBtn.innerHTML = "&#128465;";
                deleteBtn.setAttribute("class", "deleteButton");
                deleteBtn.setAttribute("onclick", "location.href ='delete-employee?id="+id+"';");
                column[4].innerHTML ='';
                column[4].appendChild(deleteBtn);
                var editBtn = document.createElement("BUTTON");
                editBtn.id ='ebtn';
                editBtn.innerHTML = "&#x1F58B;";
                editBtn.setAttribute("class", "editButton");
                editBtn.setAttribute("onclick","location.href ='edit-employee?id="+id+"';");
                column[5].innerHTML ='';
                column[5].appendChild(editBtn);
                j = j + 1;
             }
            for (var i=array.length+1 ; i<=5; i++) {
                var row = document.getElementById('contentTable').rows;
                var column = row[i].cells;
                column[0].innerHTML = "";
                column[1].innerHTML = "";
                column[2].innerHTML = "";
                column[3].innerHTML = "";
                column[4].style.display ='none';
                column[5].style.display ='none';
            }  
         } else {
             console.log('Something went wrong..!!');
         }
     }
     }
}
</script>
</body>
</html>
