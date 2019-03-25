<%@ page import="il.ac.hit.domains.WorkOuts" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="il.ac.hit.domains.Activities" %>
<%@ page import="java.util.Map" %>
<% String id = (String) request.getAttribute("id");%>
<%  Map<String,Integer> map = (Map<String, Integer>) request.getAttribute("staticsMap");
    List<WorkOuts> top3 = (List<WorkOuts>)request.getAttribute("top3");
double total = (double)request.getAttribute("total");%>

<!DOCTYPE HTML>
<html>
<head>

        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">

        <header role="banner" class="navbar navbar-fixed-top navbar-inverse">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-nav-demo" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>

                </div>
                <div class="collapse navbar-collapse" id="bs-nav-demo">
                    <%
                        session.setAttribute("id",id);
                    %>
                    <ul class="nav navbar-nav" style="font-size: 20px">
                        <li><a href="/controller/WorkoutService/load" >My Workouts</a></li>
                        <li><a href="/controller/ActivityService/load">New Workout</a></li>
                        <li><a href="/controller/StaticsService/statics">Statics</a></li>
                        <li><a href="/controller/UsersService/logout">Logout</a></li>
                    </ul>
                </div>
            </div>
        </header>
    <script>
        window.onload = function () {
            var chart1 = new CanvasJS.Chart("calories-form", {
                animationEnabled: true,
                theme: "light1", // "light1", "light2", "dark1", "dark2"
                title: {
                    text: "This is how you spitted your energy"
                },
                axisY: {
                    title: "% Out of <%=(int)total%> sets",
                    suffix: "%",
                    includeZero: false
                },
                axisX: {
                    title: "Activities"
                },
                data: [{
                    type: "column",
                    yValueFormatString: "#,##0.0#\"%\"",
                    dataPoints: [
                     <%

                     for (Map.Entry<String, Integer> entry : map.entrySet())
                    { %>
                        { label:"<%=entry.getKey()%>", y: <%=((double)entry.getValue() / total )*100%> },
                      <%}%>

                    ]
                }]
            });
            var chart2 = new CanvasJS.Chart("top3-form", {
                theme: "light1", // "light2", "dark1", "dark2"
                animationEnabled: false, // change to true
                title:{
                    text: "Top workouts during last 7 days"
                },
                data: [
                    {
                        // Change type to "bar", "area", "spline", "pie",etc.
                        type: "column",
                        dataPoints: [

                 <%  for (WorkOuts w : top3)
                { %>
                            { label:"<%=w.getWorkoutName()%>", y: <%=w.getCalories()%>  },
                            <%}%>
                        ]
                    }
                ]
            });
            chart1.render();
            chart2.render();

        }
    </script>

    </head>

<body>
<div class="container" style="margin-top:50px"><h1>Your statics is right here!</h1></div>
<div id="exTab1" class="container">


    <div class="tab-content clearfix">
        <div class="tab-pane active" id="1a">
            <div id="calories-form" style="position: relative; height:40vh; width:80vw"></div>
            <div id="top3-form" style="position: relative; height:40vh; width:80vw"></div>
        </div>
    </div>
</div>
</body>


<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
<script src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"></script>
<script src="//code.jquery.com/jquery-2.1.4.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>

</body>
</html>