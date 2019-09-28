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
    <div style="margin-left:260px; overflow:scroll;"><h2><b>Forms available</b></h2></div>
    <div style="margin-left: 250px; margin-top: 10px;">
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
                        <form action="display-form" method="GET">
                            <input type ="hidden" name = "formId" value =${form.id}>
                             <p><button>Show</button></p>
                        </form>
                        <c:if test="${role == 'ROLE_ADMIN'}">
                        <form action="form-stats" method="GET">
                            <input type ="hidden" name = "formId" value =${form.id}>
                             <p><button>Stats</button></p>
                        </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    </body>
</html>


