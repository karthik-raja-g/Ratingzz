<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<%@ include file="adminHome.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <title>VIEW RESPONSE PAGE</title>
        <link rel="stylesheet" href="resources/css/style.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    </head>
    <body class="backgroundLogin">
        <h2><center>YOUR RESPONSE</center></h2>
        <div class="container">
                    <div class="row">
                      	<div class="col-25">
                            <label for="id">Form Id</label>
                      	</div>
                  	<div class="col-75">
                    <input type="text" id="id" path="id" value="${form.id}" readonly/>
                  	</div>
                    </div>
                     <div class="row">
                      	<div class="col-25">
                            <label for="id">Form Name</label>
                      	</div>
                  	<div class="col-75">
                    <input type="text" id="id" path="id" value="${form.title}" readonly/>
                  	</div>
                    </div>
                     <div class="row">
                      	<div class="col-25">
                            <label for="id">Form Description</label>
                      	</div>
                  	<div class="col-75">
                    <input type="text" id="id" path="id" value="${form.description}" readonly/>
                  	</div>
                    </div>
            </div>
<br>
    <div class>
      <center>
      <table>
        <c:forEach items="${answers}" var="answer">
        <div class="container">
                <div class="row">
                  	<div class="col-25">
                        <label for="id">Question: </label>
                  	</div>
              	<div class="col-75">
                <input type="text" id="id" path="id" value="${answer.question.question}" readonly/>
              	</div>
                </div>
                 <div class="row">
                  	<div class="col-25">
                        <label for="id">Your choice</label>
                  	</div>
              	<div class="col-75">
                <input type="text" id="id" path="id" value="${answer.answer}" readonly/>
              	</div>
                </div>
                 <div class="row">
                  	<div class="col-25">
                        <label for="id">Comments</label>
                  	</div>
              	<div class="col-75">
                <input type="text" id="id" path="id" value="${answer.comments}" readonly/>
              	</div>
                </div>
        </div>
        </c:forEach>
      </table>
      </center>
    </div>
  </body>
</html>
