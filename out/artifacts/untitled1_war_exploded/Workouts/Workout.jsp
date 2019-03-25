<%@ page import="il.ac.hit.domains.WorkOuts" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="il.ac.hit.domains.Activities" %>
<% String id = (String) request.getAttribute("id");%>
<% List<WorkOuts> lst = (List<WorkOuts>) request.getAttribute("workoutList");%>

<!DOCTYPE html>
    <html lang="en">
    <head>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <style type="text/css">
            <%@include file="workoutcss.css" %>
        </style>

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


    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>


<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="table-responsive">
                <%
                    session.setAttribute("id2",id);
                %>
                <table summary="This table shows how to create responsive tables using Bootstrap's default functionality" class="table table-bordered table-hover">
                    <caption class="text-center">An example of a responsive table based on <a href="https://getbootstrap.com/css/#tables-responsive" target="_blank">Bootstrap</a>:</caption>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Date</th>
                        <th>Edit</th>

                    </tr>
                    </thead>
                    <tbody>
                        <%
                            Iterator<WorkOuts> it = lst.iterator();
                            while (it.hasNext())
                            {
                                WorkOuts node = it.next();%>
                    <tr id="<%=node.getWorkourId()%>">

                        <td>
                            <%= node.getWorkoutName() %>
                        </td>
                        <td>
                            <%= node.getWorkOutDate().toString().substring(0,10)%>
                        </td>
                        <td>
                            <button type="button" class="detailed-list" id="<%=node.getWorkourId()%>" data-toggle="modal" data-target="#myModal">
                                Details
                            </button>
                        </td>
                    </tr><%

                            }
                        %>

                </table>
            </div><!--end of .table-responsive-->
        </div>
    </div>
</div>






<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <div class="modal-body">
                <table class="modal-table">
                    <tr>
                        <th>Name</th>
                        <th>Sets</th>
                        <th>Calories</th>
                    </tr>
                    <tbody id="table-body">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="update-workout">Update</button>
                <button type="button" class="delete-workout" data-dismiss="modal">Delete</button>
                <button type="button" class="close-modal" id="close-btn" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/ui/1.11.3/jquery-ui.min.js"></script>
<script src="//code.jquery.com/jquery-2.1.4.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>

<script>
    var workout_id = 0;
    var map = new Object();
<%
 for(int i = 0; i<lst.size();i++)
 {
      %>
    var arrAct = [];
   <%
    for(int j = 0; j<lst.get(i).getActivitiesList().size();j++)
        {%>
            var item =  {activityName:"<%=lst.get(i).getActivitiesList().get(j).getActivityName()%>",repeats:<%=lst.get(i).getActivitiesList().get(j).getRepeats()%>,calories:<%=lst.get(i).getActivitiesList().get(j).getCalories()%>}
            arrAct.push(item)<%
        }
        %>
    map[<%=lst.get(i).getWorkourId()%>]=arrAct; <%
 }

 %>

<%@include file="wojs.js" %>
</script>

</body>
</html>