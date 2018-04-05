<%-- 
    Document   : Patient
    Created on : Mar 13, 2018, 9:20:48 PM
    Author     : miguel
--%>

<%@page import="Business.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Patient</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="./assets/bootstrap-datetimepicker.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
        <script src="./assets/bootstrap-datetimepicker.min.js"></script>
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
            .appointment img{
                float: left;
                padding-right: 30px;
            }

            .appointmentText p h5{
                padding-left: 20px;
                margin-left: 20px;
            }

            .appointmentForm button{
                margin-top: 30px;

            }

            .formSelection{
                margin-top: 40px;
            }

            .updateBtn{
                margin-top: 20px;
                margin-bottom: 20px;
            }

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

            .mainContainer{
                margin-top: 50px;
                margin-bottom: 50px;
            }
        </style>
    </head>
    <body>
        <%@include file="NavBar.jsp" %>

        <div class="jumbotron">
            <h1 class="display-4 text-center">Ear To Ear Dentistry</h1>

        </div>

        <div class="container mainContainer">

            <h1>Welcome <%= patient.getFirstName() + " " + patient.getLastName()%></h1>
            <a class="btn btn-outline-primary updateBtn" href="./updatePatient.jsp" role="button">Update Account</a>

            <% if (patient.hasAppointment()) {%>
            <h3 class="text-center">Thank You For Your Business</h3>
            <div class="card w-80 ">
                <div class="card-body appointment">
                    <img src="<%= procedure.getImageUrl()%>" class="img-responsive rounded" style="height: 180px;"/>
                    <div class="appointmentText">
                        <h5 class="card-title">Your upcoming appointment is <%= appointment.getApptDateTime()%></h5>
                        <p class="card-text">Dentist: Dr. <%= dentist.getLastName()%></p>
                        <p class="card-text">Procedure: <%= procedure.getProcedureName()%></p>
                        <p class="card-text">Description: <%= procedure.getDescription()%></p>
                        <p class="card-text">Price: $<%= procedure.getCost()%></p>
                        
                        <form method="POST" action="changeAppointment">
                            <div style="display: none;">
                                <input name="patientIdTb" value="<%= patient.getId() %>"/>
                            </div>
                            <button type="submit" class="btn btn-outline-primary">Update Appointment</button>
                        </form>
                        
                    </div>


                </div>
            </div> <% } else {%>


            <h1 class="text-center">Manage Your Next Visit</h1>
            <form method="POST" action="createAppointmentServlet" class="appointmentForm">
                <div class="row formSelection">
                    <div class="col-lg-4">
                        <strong><p>Pick An Appointment Date</p></strong>
                        <input size="28" type="text" name="dateTimePicker" value="March-20-2018 8:00am" readonly class="form_datetime">
                    </div>
                    <div class="col-lg-4">
                        <strong><p>Pick A Procedure</p></strong>
                        <select multiple size="10" class="form-control" id="exampleFormControlSelect1" name="procedureChoice">
                            <option value="P114">Cleaning/Exam</option>
                            <option value="P119">X-Ray</option>
                            <option value="P122">Whitening</option>
                            <option value="P321">Cavity</option>
                            <option value="P650">Top Dentures</option>
                            <option value="P660">Bottom Dentures</option>
                            <option value="P780">Crown</option>
                            <option value="P790">Root Canal</option>
                            <option value="P910">Replace Teeth</option>
                        </select>
                        <div style="display: none;">
                            <input value="<%= patient.getId()%>" name="patientIdTb" > 
                        </div>


                    </div>
                    <div class="col-lg-4">
                        <strong><p>Pick A Dentist</p></strong>
                        <select multiple size="4" class="form-control" id="exampleFormControlSelect1" name="dentistChoice">
                            <option value="D201">Dr. Miller</option>
                            <option value="D202">Dr. Cassidy</option>
                            <option value="D203">Dr. York</option>
                            <option value="D204">Dr.Pettersen</option>
                        </select>
                    </div>

                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>         


            </form>

            <% }%>        


        </div>

        <script type="text/javascript">


            $(".form_datetime").datetimepicker({
                format: 'MM-dd-yyyy H:iip',
                todayHighlight: true,
                todayBtn: true,
                minuteStep: 15,
                showMeridian: true,
                startDate: new Date(),
                autoClose: true});

            $(".form_datetime").datetimepicker('show');
        </script>

        <%@include file="Footer.jsp" %>
