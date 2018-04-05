<%-- 
    Document   : Dentist
    Created on : Mar 13, 2018, 9:35:20 PM
    Author     : miguel
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.*" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dentist</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="./styles/Dentist.css"/>
        <style>
            .jumbotron {
                background-image: url("https://www.deltadentalvablog.com/wp-content/uploads/2013/10/female-dentist-holding-x-ray.jpg");
                background-color: #17234E;
                margin-bottom: 0;
                min-height: 100%;
                background-repeat: no-repeat;
                background-position: center;
                -webkit-background-size: cover;
                background-size: cover;
            }

            .jumbotron h1{
                letter-spacing: 10px;
                color: white;
                font-size: 5.2em;
            }


            .jumbotron h3{
                color: #353A3F;
                font-size: 3.0em;
            }
        </style>
        <%
            Dentist dentist = new Dentist();
            dentist = (Dentist) session.getAttribute("Dentist");
            ArrayList<Appointment> appointmentList = dentist.getAppointmentList().getAppointmentList();

        %>
    </head>
    <body>
        <%@include file="NavBar.jsp" %>

    <div class="jumbotron">
        <h1 class="display-4 text-center">Ear To Ear Dentistry</h1>

    </div>

        <div class="heading container">
            <h1>Welcome back, <%= dentist.getFirstName()%></h1>
            <p><span style="color: blue;">Dentist ID:</span> <%= dentist.getId()%></p>
            <p><span style="color: blue;">First Name: </span><%= dentist.getFirstName()%></p>
            <p><span style="color: blue;">Last Name: </span><%= dentist.getLastName()%></p>
            <a class="btn btn-outline-primary" href="./updateDentist.jsp" role="button">Update Account</a>
            <h3 class="text-center">Here are your upcoming appointments:</h3>

        </div>


        <div class="container">
            <div class="row">
                <table class="table table-hover table-bordered">
                    <thead>
                    <th>Date and Time</th>
                    <th>Patient</th>
                    <th>Procedure Code</th>
                    <th>Procedure</th>
                    </thead>
                    <% for (int i = 0; i < appointmentList.size(); i++) {
                        Appointment appt = appointmentList.get(i);

                        Patient patient = new Patient();
                        patient.selectFromDbWithId(appt.getPatientId());

                        Procedure procedure = new Procedure();
                        procedure.selectFromDb(appt.getProcedureCode());
                %>
                <tr>
                    <td><%= appt.getApptDateTime()%></td>
                    <td>
                        <%= patient.getFirstName() + " " + patient.getLastName()%>
                    </td>
                    <td>
                        <%= appt.getProcedureCode()%>
                    </td>
                    <td>
                        <%= procedure.getProcedureName()%>
                    </td>
                </tr>
                
                <% }%>
                </table>

            </div>

        </div>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    </body>
</html>
