
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.esd.cw.model.User"%>

<%
    User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

        <!-- jQuery UI -->        
        <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

        <!-- BootStrap -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

        <style>
            body{
                text-transform: uppercase;
                color: pink;
            }
            .info-heading {
                color: green;
            }
            .user-information-section .user-information-block {
                display: block;
            }
            .information-value {
                margin-top: -5px;
                margin-bottom: 10px;
            }
        </style>

        <!-- Viewport -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ESD</title>
    </head>
    <body>

        <!-- navbar start -->
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">ESD</a>
                </div>

                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <%
                            if (user != null) {
                                if (user.isIsAdmin()) {
                        %>
                        <li><a href="admin/dashboard">Admin Dashboard</a></li>
                            <%
                            } else {
                            %>
                        <li><a href="dashboard">Dashboard</a></li>                                
                            <%
                                    }
                                }
                            %>

                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <%
                            if (user != null) {
                        %>
                        <li><a href="logout">Logout</a></li>
                            <%
                            } else {
                            %>
                        <li><a href="login">Login</a></li>
                        <li><a href="register">Register</a></li>
                            <%
                                }
                            %>
                    </ul>
                </div>
            </div>
        </nav>
        <!-- navbar end -->

        <!-- main container -->
        <div class="container">