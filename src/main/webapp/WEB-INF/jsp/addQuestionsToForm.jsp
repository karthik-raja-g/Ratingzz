<!--<%@ include file="adminHome.jsp"%>-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
</head>
<body style="background-color:#4DD4F9;">
<div style="margin-left:250px;">
<div style="width:max-width; height:10%; background-color:#4DD4F9;">
<center><h2> ${form.title} </h2></center>  
</div>
<div style="width:max-width; height:15%; background-color:#4DD4F9;">
<center>
<div style="display: inline-block; align-items: center; background-color:white;">
<table>
<tr><td>
<input class="form-control form-control-lg" style="width:450px; height:50px;" name="question" type="text" id ="question" placeholder="Type your question here">
<td> <button style="height:50px;" onclick="saveQuestion(${form.id})"> Add question </button> </td></tr>
</table>
</div>
</center>
</div>
<div style="overflow: scroll; height:65%; width:max-width; margin-right:5%; text-align: center; background-color:white;">
<table align="center" id="previewTable">
</table>
</div>
<div style="height:10%; width:max-width;">
<form action="display-form" method="post">
<input type="hidden" name="formId" value="${form.id}"/>
<center><button type="submit"> Save </button> </center>
</form>
</div>
</div>
<script>
function saveQuestion(formId) {
    httpRequest = new XMLHttpRequest();
    var question = document.getElementById("question").value;
    var previewTable = document.getElementById("previewTable");
    if (!httpRequest) {
        console.log('Unable to create XMLHTTP instance');
        return false;
    }
    if(question != "") {
        httpRequest.open('GET', 'add-question?formId='+formId+'&question='+question);
        httpRequest.responseType = 'json';
        httpRequest.send();
        httpRequest.onreadystatechange = function() {
            if (httpRequest.readyState === XMLHttpRequest.DONE) {
                if (httpRequest.status === 200) {
                    var savedQuestion = httpRequest.response;

                    // Adding question to preview
                    var questionRow = document.createElement("tr");
                    var questionColumn = document.createElement("td");
                    questionColumn.colSpan = "5";
                    questionColumn.innerHTML = savedQuestion.question;
                    questionRow.appendChild(questionColumn);
                    previewTable.appendChild(questionRow);
                    

                    // Adding Options to preview
                    var optionsRow = document.createElement("tr");
                    for(var i = 1; i < 6; i++) {
                        var optionsColumn = document.createElement("td"); 
                        optionsRow.appendChild(optionsColumn);
                        var radioButton = document.createElement("input");
                        radioButton.setAttribute("type", "radio");
                        radioButton.setAttribute("id", i);
                        radioButton.setAttribute("name", i);
                        var text = document.createTextNode(i);
                        optionsColumn.appendChild(radioButton);
                        optionsColumn.appendChild(text);
                        optionsRow.appendChild(optionsColumn);
                    }
                    previewTable.appendChild(optionsRow);

                    // Adding Comments to Preview
                    var commentRow = document.createElement("tr");
                    var commentColumn = document.createElement("td");
                    commentColumn.colSpan = "5";
                    var textBox = document.createElement("input");
                    textBox.setAttribute("type", "text");
                    commentColumn.appendChild(textBox);
                    commentRow.appendChild(commentColumn);
                    previewTable.appendChild(commentRow);
                    
                } else {
                    console.log('Something went wrong..!!');
                }
            }  
        }
    }
}
</script>
</body>
</html>
