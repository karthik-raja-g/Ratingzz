<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.List" %>
<html>
    <head>
        <title>ROLE DISPLAY</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="resources/css/displayStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>

        <table align = "center" border = 0 cellpadding=5>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Status</th>
                <th>Created Date</th>
                <th>Modified Date</th>
                <th colspan="2">Actions</th>                
            </tr>
            <c:forEach items="${roles}" var="role">
                <tr>
                    <td><c:out value="${role.id}"/></td>
                    <td><c:out value="${role.name}"/></td>
                    <td><c:out value="${role.description}"/></td>
                    <td><c:out value="${role.isActive}"/></td>
                    <td><c:out value="${role.createdDate}"/></td>
                    <td><c:out value="${role.modifiedDate}"/></td>
                    <td>
                        <form method = "post" action = "edit-role">
                            <input type ="hidden" name = "roleId" value ="${role.id}" />
                            <button type = "Submit" class="btn btn-info">
                            <span class="glyphicon glyphicon-pencil"></span> Edit</button>
                        </form>
                    </td>
                    <td>
                        <form method = "post" action = "delete-role">
                            <input type ="hidden" name = "roleId" value ="${role.id}" />
                            <button type = "Submit" class="btn btn-info">
                            <span class="glyphicon glyphicon-trash"></span> Delete</button>
                        </form>
                    </td>
                </tr>
           </c:forEach>
        </table>
        <div style="text-align: center">
            <form method = "post" action = "create-role">
                <br><button type="submit" class="btn btn-success">
                <span class="glyphicon glyphicon-plus"></span> Add role</button>
            </form>
        </div>
        <div class="back-button">
            <button type="submit" onclick="history.back()" class="btn btn-danger">
             <span class="glyphicon glyphicon-circle-arrow-left"></span> Back</button>
        </div>
        <form action = "logout">
            <div class="logout-button">
                <button type = "Submit" class="btn btn-danger">
                <span class="glyphicon glyphicon-log-out"></span> Log out</button>
            </div>
        </form>
        <form action = "employee-home">
            <div class="home-button">
                <button type = "Submit" class="btn btn-danger">
                <span class="glyphicon glyphicon-home"></span> Home</button>
            </div>
        </form>
    </body>
</html>
