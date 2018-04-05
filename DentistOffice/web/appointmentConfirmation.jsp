<%-- 
    Document   : appointmentConfirmation
    Created on : Mar 21, 2018, 7:16:48 PM
    Author     : miguel
--%>
<%@page import="Business.*" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Confirmation</title>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
            <link rel="stylesheet" href="./assets/bootstrap-datetimepicker.min.css">
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
            <%
            Patient patient = new Patient();
            patient = (Patient) session.getAttribute("Patient");

            Appointment appointment = patient.getAppointment();

            Dentist dentist = new Dentist();
            dentist.selectFromDatabaseWithId(appointment.getDentistId());

            Procedure procedure = new Procedure();
            procedure.selectFromDb(appointment.getProcedureCode());

        %>
        <style>
            .jumbotron {
                background-image: url("https://parkavenuedentalgnv.com/wp-content/uploads/2015/11/kid-at-dentist.jpg");
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
        </head>

        <body>
            <%@include file="NavBar.jsp" %>
        
        <div class="jumbotron">
    <h1 class="display-4 text-center">Ear To Ear Dentistry</h1>

</div>
            <div class="container">
                <h1 class="text-center">Confirmation</h1>
                <h3 class="text-center">Thank You For Making An Appointment With Ear To Ear Dentistry</h3>
                <h3 class="text-center">We Will See You Soon</h3>
                <p class="text-center">Your appointment date is <%= appointment.getApptDateTime() %></p>
                <p class="text-center">With Dr. <%= dentist.getLastName() %></p>
                <p class="text-center">For a <%= procedure.getProcedureName() %></p>
                <p class="text-center"><%= procedure.getDescription() %></p>
                <p class="text-center">Price is $<%= procedure.getCost() %></p>
                <a class="btn btn-primary btn-block" href="./Patient.jsp">Go Back To Account Page</a>
            </div>


<%@include file="Footer.jsp" %>