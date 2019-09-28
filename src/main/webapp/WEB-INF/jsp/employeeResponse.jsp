<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import ="com.ideas2it.ratingsystem.model.Question" %>
<%@ page import ="com.ideas2it.ratingsystem.model.Form" %>
<html>
<head>
<title> Fill form </title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
<div class= "create">
<br>
<form action="add-answers" method="post">
<% Form form = (Form)request.getAttribute("form"); %>
<center><h1>Form name : ${form.title}</h1></center>
<table style="margin:auto;">
<input type="hidden" name="formId" value="<%=form.getId()%>" />
<c:set var="questionNumber" value="0" />
<%for(Question question : form.getQuestions()) { %>
    <c:set var="questionNumber" value="${questionNumber + 1}" />
        <div class="container">
                <div class="row">
                  	<div class="col-25">
                        <label for="id">Question: </label>
                  	</div>
              	<div class="col-75">
                <input type="text" id="id" path="id" value="<%=question.getQuestion()%> " />
              	</div>
                </div>
    <%String questionId = String.valueOf(question.getId());%>
    <% String questionString = questionId+"_radio";%>
    <% String comments = questionId+"_comments";%>
                 <div class="row">
                  	<div class="col-25">
                        <label for="id">Options</label>
                  	</div>
              	<div class="col-75">
                <input type="radio" name="<%=questionString%>" value="1">1
                <input type="radio" name="<%=questionString%>" value="2" >2
                <input type="radio" name="<%=questionString%>" value="3" >3
                <input type="radio" name="<%=questionString%>" value="4" >4
                <input type="radio" name="<%=questionString%>" value="5" >5
              	</div>
                </div>
                 <div class="row">
                  	<div class="col-25">
                        <label for="id">Comments</label>
                  	</div>
              	<div class="col-75">
                <input type="text" name="<%=comments%>" placeholder="comments">
              	</div>
                </div>
 <%}%>
<button type="submit"> Submit </button>
</table>  
</form>
</div>
</div>
</body>
</html>

