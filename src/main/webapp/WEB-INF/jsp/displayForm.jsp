<%@ include file="adminHome.jsp"%>
<%@ page import ="java.util.List" %>
<html>
    <head>
        <title>FORM DISPLAY</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="resources/css/style.css">
    </head>
     <body class="backgroundLogin">
        <h2><center>Form Display</center></h2>
        <div class="container">
                    <div class="row">
                      	<div class="col-25">
                            <label for="name">TITLE</label>
                      	</div>
                  	<div class="col-75">
                    <input type="text"  value="${form.title}" readonly><br>
                  	</div>
                    </div>
                    <div class="row">
                      	<div class="col-25">
                            <label for="name">DESCRIPTION</label>
                      	</div>
                  	<div class="col-75">
                    <input type="text" value="${form.description}" readonly><br>
                  	</div>
                    </div>
                    <div class="row">
                      	<div class="col-25">
                            <label for="name">CREATED DATE</label>
                      	</div>
                  	<div class="col-75">
                    <input type="text" value="${form.createdDate}" readonly><br>
                  	</div>
                    </div>
                   <form method = "post" action = "edit-form">
                        <input type="submit"  value="EDIT" class="button button1"/>
                   </from>
                    <form method="post" action="delete-form">
                        <input type="submit"  value="DELETE" class="button button1"/>
                    </form>
        </div>
            <div style="overflow:scroll;">
        <div class="container">
                <c:forEach items="${form.questions}" var="question">
                <div class="row">
                  	<div class="col-25">
                        <label for="id">Question: </label>
                  	</div>
              	<div class="col-75">
                <input type="text" id="id" path="id" value="${question.question}" />
              	</div>
                </div>
                <br>
                 <div class="row">
                  	<div class="col-25">
                        <label for="id">Options</label>
                  	</div>
              	<div class="col-75">
                        <input type="radio" name="option" /> 1 
                        <input type="radio" name="option"/> 2
                        <input type="radio" name="option"/> 3 
                        <input type="radio" name="option"/> 4 
                        <input type="radio" name="option"/> 5 
                </div>
                </div>
                 <div class="row">
                  	<div class="col-25">
                        <label for="id">Comments</label>
                  	</div>
              	<div class="col-75">
                <input type="text" name="comments" placeholder="comments" readonly="true">
              	</div>
                </div>
                <br><br>
                </c:forEach>
            </div>
            <div>
               <form method = "post" action = "publish-form">
                    <input type="hidden"  name = "formId" value="${form.id}" />
                    <input type="submit" class="button button1" value="publish-form"/>
               </from>
            </div>
    </body>
</html>
         
