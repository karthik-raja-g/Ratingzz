<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="adminHome.jsp"%>
<html>
    <head>
        <title>FORM DISPLAY</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="resources/css/viewrole.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
  <body>
    <div style="margin-left:260px;"><h2><b>Your submitted forms. Select one to view</b></h2></div>
    <div style="margin-left: 250px; margin-top: 10px; position: fixed;overflow:scroll;">
        <c:forEach items="${forms}" var="form">
            <div class="flip-card">
                <div class="flip-card-inner">
                    <div class="flip-card-front">
                        <img src="resources/img/form.jpg" alt="Avatar" style="width:200px;height:200px;">
                        <br></br>
                        <p><b>${form.title}</b></p>
                    </div>
                    <div class="flip-card-back">
                        <br><br>
                        <p><b>Title: <b><i>${form.title}</i></p> 
                        <p><b>Email : <b><i>${form.description}</i></p> 
                        <p><b>No. of questions : <b>${fn:length(form.questions)}</p>
                        <p><b>Created Date : <b><i>${form.createdDate}</i></p>
                        <form action="get-response-by-form-employee" method="GET">
                            <input type ="hidden" name = "formId" value =${form.id}>
                            <input type ="hidden" name = "employeeId" value =${employeeId}>
                        
                             <p><button>Show</button></p>
                        </form>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    </body>
</html>


