<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
<%@ include file="adminHome.jsp"%>
<!DOCTYPE html>
<html>

    <head>
        <title>ROLE CREATION</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="resources/css/displayUserStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
        <title>ROLE MANAGEMENT</title>
        <link rel="stylesheet" href="resources/css/style.css"> 
    </head>
    <body class="backgroundLogin">
        <h2><center>CREATE ROLE</center></h2>
        <div class="container">
              <form:form action="add-role" modelAttribute="role" method="post">
                    <div class="row">
                        <div class="col-25">
                            <label for="name">ROLE NAME</label>
                        </div>
                    <div class="col-75">
                    <form:input type="text" id="name" autocomplete="off" path="name" title="please enter valid name" placeholder="Role Name" required="required"/>
                    </div>
                    </div>
                    <div class="row">
                        <div class="col-25">
                            <label for="name">ROLE DESCRIPTION</label>
                        </div>
                    <div class="col-75">
                    <form:input type="text" id="name" autocomplete="off" path="description" title="please enter valid name" placeholder="Role Description" required="required"/>
                    </div>
                    </div>
                    <input type="submit" value="SUBMIT" class="button button1"/>
                </form:form>
        </div>

            <div class="back-button">
                <button type="submit" onclick="history.back()" class="btn btn-success">
                 <span class="glyphicon glyphicon-circle-arrow-left"></span> Back</button>
            </div>
            <form action = "employee-home">
                <div class="home-button">
                    <button type = "Submit" class="btn btn-success">
                    <span class="glyphicon glyphicon-home"></span> Home</button>
                </div>
            </form>
    </body>
</html>


