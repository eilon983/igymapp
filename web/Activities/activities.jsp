<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="il.ac.hit.domains.Activities" %>
<%@ page import="java.util.Iterator" %>
<% List<Activities> lst = (List<Activities>) request.getAttribute("actvListDB");%>
<% String id = (String) request.getAttribute("id");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="//use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">


   <style>
       <%@include file="activitiescss.css"%>
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


        <div class="dual-list list-right col-md-6">
            <div class="well">
                <div class="row" style="margin: 0px auto">
                    <div class="col-md-12 form-group">
                       <h1>Activities:</h1>
                    </div>
                </div>
                <div class="row" style="margin: 0px auto">
                    <div class="col-md-12 form-group">
                        <div class="input-group">
                            <span class="input-group-addon glyphicon glyphicon-unchecked selector" style="cursor: pointer; top: 0px; font-size: 15px;" title="Select All"></span>
                            <span class="input-group-addon glyphicon glyphicon-plus move-left" style="cursor: pointer; top: 0px; font-size: 15px;" title="Add Selected"></span>
                        </div>
                    </div>
                </div>

                <ul class="list-group" id="dual-list-right">

                     <%
                         Iterator<Activities> it = lst.iterator();
                         while (it.hasNext())
                         {   %> <li class="list-group-item"><%= it.next().getActivityName() %></li> <% }
                         %>

                </ul>
            </div>
        </div>
        <div class="dual-list list-left col-md-6">
            <div class="well text-right">
                <div class="row" style="margin-top: 30px ">
                    <div class="col-md-12 form-group">
                        <input type="text" id="actvName" class="form-control" id="formGroupExampleInput" placeholder="Enter Workout name"  style="font-size: 15px; height: 60px">
                    </div>
                </div>

                <div class="row" style="margin: 0px auto">
                    <div class="col-md-12 form-group">
                        <div class="input-group">

                            <span class="input-group-addon glyphicon glyphicon-unchecked selector" style="cursor: pointer; top: 0px; font-size: 15px;" title="Select All"></span>
                            <span class="input-group-addon glyphicon glyphicon-minus move-right" style="cursor: pointer; top: 0px; font-size: 15px;" title="Remove Selected"></span>
                            <span class="input-group-addon glyphicon save-list" data-toggle="modal" data-target="#exampleModal" style="cursor: pointer; top: 0px; font-size: 15px;" title="Save"><i class="far fa-save"></i></span>
                        </div>
                    </div>
                </div>

                <ul class="list-group" id="dual-list-left">
                </ul>
            </div>
        </div>



    </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="font-size: 10px;">
<div class="modal-dialog" role="document">
    <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel"  style="font-size: 10px;">Put sets please</h5>

        </div>
        <div class="modal-body" >
            <ul class="list-group" id="modal-list">
            </ul>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-secondary edit-list" data-dismiss="modal"  style="font-size: 10px;">Close</button>
            <button type="button" class="btn btn-primary save-btn" data-dismiss="modal"  style="font-size: 10px;">Save changes</button>

        </div>
    </div>
</div>
</div>

<script src="//code.jquery.com/jquery-2.1.4.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>

<script>
    var map = new Object(); // or var map = {};
    <%

                        Activities a = null;
                       for(int i = 0; i < lst.size();i++)
                      {  a = lst.get(i);
                          %> map["<%=a.getActivityName()%>"]=<%=a.getCalories()%>
                                <% }
                         %>
    <%@include file="activitesjs.js"%>
</script>

</body>
</html>