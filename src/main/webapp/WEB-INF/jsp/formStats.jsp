<!DOCTYPE html>
<%@ page import ="java.util.List" %>
<%@ page import ="java.util.Map" %>
<%@ page import ="java.util.Set" %>
<%@ page import ="com.ideas2it.ratingsystem.model.Question" %>
<%@ page import ="com.ideas2it.ratingsystem.model.Form" %>
<%@ include file="adminHome.jsp"%>
<html>
  <head>
<title>Pie Chart - User Input</title>  
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<style>
    
    #piechart {
    height: 600px;
    }
    
    label {
    display: inline-block;
    width: 100px;
    }   
</style>
</head>
<body>
    <%  Map<Question,Map<Integer,Integer>> stats = (Map<Question,Map<Integer,Integer>>) request.getAttribute("stats") ;
        Set<Question> questions = (Set<Question>)request.getAttribute("questions") ;
        for(Question question : questions) {%>
        <form id="form1" onclick="drawChart(); return false" >
            <label for="1star">1 Star: </label><input type="number" value ="<%=stats.get(question).get(1)%>" id="1star"><br>
            <label for="2star">2 Star: </label><input type="number"  value ="<%=stats.get(question).get(2)%>" id="2star"><br>
            <label for="3star">3 Star: </label><input type="number" value ="<%=stats.get(question).get(3)%>" id="3star"><br>
            <label for="4star">4 Star: </label><input type="number" value ="<%=stats.get(question).get(4)%>" id="4star"><br>
            <label for="5star">5 star: </label><input type="number" value ="<%=stats.get(question).get(5)%>" id="5star"><br>
            <button type="submit" value="Submit">Submit</button>
        </form>
        <div id="piechart"></div>
        <%}%> 
  <script>
      //define the chart package
      google.charts.load('current', {'packages':['corechart']});
      //set what is supposed to happen when the page loads. You typically want a state of the chart to show on load, but in this case, there is no data on load.
      //google.charts.setOnLoadCallback(drawChart);
     
     //submit requires text inputs to use parseInt to work as numbers
      function drawChart() {
       one = parseInt(document.getElementById('1star').value);
       two = parseInt(document.getElementById('2star').value);
       three = parseInt(document.getElementById('3star').value);
       four = parseInt(document.getElementById('4star').value);
       five = parseInt(document.getElementById('5star').value);
       
       //replace data with variable names
       var data = google.visualization.arrayToDataTable([
          ['Stars', 'No. of hits'],
          ['1 Star',     one],
          ['2 Stars',      two],
          ['3 Stars',  three],
          ['4 Stars', four],
          ['5 Stars',    five],
        ]);
        var options = {
          title: 'Weekly Activities'
        };
    //the id is the DOM location to draw the chart    
    var chart = new google.visualization.PieChart(document.getElementById('piechart'));
    chart.draw(data, options);
      }
    </script> 
      
</body>
</html>

