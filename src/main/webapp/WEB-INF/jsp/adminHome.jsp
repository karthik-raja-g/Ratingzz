<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="resources/css/style.css"> 
    </head>
    <body class="menu-background">
        <form method="post">
        <div align="right">
            <a href="logout" class="button button1">Logout</a>
        </div>
        <div id="mySidenav" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
              <c:if test="${role == 'ROLE_ADMIN'}">
                <a href="display-subordinates">Managers</a>
                <a href="display-subordinates">Team Leads</a>
                <a href="view-employees">Employees</a>
                <a href="display-all-forms">View Stats</a>
                <a href="new-form">Create Form</a>
                <a href="display-all-forms">Display Forms</a>
              </c:if>
                <c:if test="${role == 'ROLE_MANAGER'}">
                <a href="display-subordinates">Team Leads</a>
                <a href="view-employees">Employees</a>
              </c:if>
              <c:if test="${role == 'ROLE_TEAM_LEADER'}">
                <a href="display-subordinates">Employees</a>
              </c:if>
              <a href="profile">Profile</a>
              <a href="get-answers-by-employee">View submitted responses</a>
              </div>
        </div>
        <span style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776; Menu</span>
        <script>
            function openNav() {
                document.getElementById("mySidenav").style.width = "250px";
            }
            function closeNav() {
                document.getElementById("mySidenav").style.width = "0";
            }
        </script>
        </form>
    </body>
</html> 

