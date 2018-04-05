<%-- 
    Document   : updatePatient
    Created on : Mar 21, 2018, 8:17:34 PM
    Author     : miguel
--%>

<%@page import="Business.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Account</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <%
            Patient patient = new Patient();
            patient = (Patient) session.getAttribute("Patient");

        %>

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

            .mainContainer{
                margin-top: 50px;
                margin-bottom: 50px;
            }
        </style>
    </head>
    <%@include file="NavBar.jsp" %>

    <div class="jumbotron">
        <h1 class="display-4 text-center">Ear To Ear Dentistry</h1>

    </div>
    <body>

        <div class="container mainContainer">
            <h1 class="text-center" style="margin-bottom: 30px;">Your Account</h1>
            <div class="row">
                <div class="col-lg-6 currentInfo">
                    <h2>Current Information</h2>
                    <p><span style="color: blue;">Patient ID:</span> <%= patient.getId()%></p>
                    <p><span style="color: blue;">First Name: </span><%= patient.getFirstName()%></p>
                    <p><span style="color: blue;">Last Name: </span><%= patient.getLastName()%></p>
                    <p><span style="color: blue;">Email: </span><%= patient.getEmail()%></p>
                    <p><span style="color: blue;">Address: </span><%= patient.getAddress()%></p>
                    <p><span style="color: blue;">Insurance </span><%= patient.getInsurance()%></p>
                    <button type="button" class="btn btn-outline-primary"><a href="./Patient.jsp">Back To Account</a></button>
                </div>
                <div class="col-lg-6">
                    <h2>Update Information</h2>
                    <p>Please enter your new information</p>

                    <form method="POST" action="updatePatientServlet">
                        <div class="form-group col-lg-6" style="display: none;">
                            <label for="exampleInputEmail1">Dentist ID:</label>
                            <input type="radio" name="patientIdTB" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" 
                                   value="<%= patient.getId()%>" checked>

                        </div>
                        <div class="row">

                            <div class="form-group col-lg-6">
                                <label for="exampleInputEmail1">First Name</label>
                                <input type="text" name="firstNameTB" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" 
                                       placeholder="<%= patient.getFirstName()%>" required>

                            </div>
                            <div class="form-group col-lg-6">
                                <label for="exampleInputPassword1">Last Name</label>
                                <input required type="text" name="lastNameTB" class="form-control" id="exampleInputPassword1" placeholder="<%= patient.getLastName()%>">
                            </div>
                            <div class="form-group col-lg-6">
                            <label for="exampleInputPassword1">Email</label>
                            <input type="text" name="emailTB" class="form-control" id="exampleInputPassword1" 
                                   placeholder="<%= patient.getEmail()%>" required>
                        </div>
                        <div class="form-group col-lg-6">
                            <label for="exampleInputPassword1">Password</label>
                            <input required type="password" name="passwordTB" class="form-control" id="exampleInputPassword1" placeholder="Enter Your New or Existing Password">
                        </div>
                        </div>



                        
                        <div class="form-group">
                            <label for="exampleInputPassword1">Address</label>
                            <input required type="text" name="addressTB" class="form-control" id="exampleInputPassword1" placeholder="<%= patient.getAddress()%>">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputPassword1">Insurance</label>
                            <input required type="text" name="insuranceTB" class="form-control" id="exampleInputPassword1" placeholder="<%= patient.getInsurance()%>">
                        </div>
                        

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>


                </div>
            </div>
        </div>

        <%@include file="Footer.jsp" %>