<%@page import="Business.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Information</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">

        <style>
            p{
                font-size: 1.4em;
            }
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

        <%
            Dentist dentist = new Dentist();
            dentist = (Dentist) session.getAttribute("Dentist");
        %>
    </head>
    <body>
        <%@include file="NavBar.jsp" %>

        <div class="jumbotron">
            <h1 class="display-4 text-center">Ear To Ear Dentistry</h1>

        </div>
        <div class="container mainContainer">
            <h1 class="text-center">Account In</h1>
            <div class="row">
                <div class="col-lg-6 currentInfo">
                    <h2>Current Information</h2>
                    <p><span style="color: blue;">Dentist ID:</span> <%= dentist.getId()%></p>
                    <p><span style="color: blue;">First Name: </span><%= dentist.getFirstName()%></p>
                    <p><span style="color: blue;">Last Name: </span><%= dentist.getLastName()%></p>
                    <p><span style="color: blue;">Email: </span><%= dentist.getEmail()%></p>
                    <p><span style="color: blue;">Office Number: </span><%= dentist.getOffice()%></p>
                </div>
                <div class="col-lg-6">
                    <h2>Update Information</h2>
                    <p>Please enter your new information</p>

                    <form method="POST" action="updateDentistServlet">
                        <div class="form-group" style="display: none;">
                            <label for="exampleInputEmail1">Dentist ID:</label>
                            <input type="radio" name="dentistIdTB" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" 
                                   value="<%= dentist.getId()%>" checked>

                        </div>
                        <div class="row">
                            <div class="form-group col-lg-6">
                                <label for="exampleInputEmail1">First Name</label>
                                <input required type="text" name="firstNameTB" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" 
                                       placeholder="<%= dentist.getFirstName()%>">

                            </div>
                                       <div class="form-group col-lg-6">
                            <label for="exampleInputPassword1">Last Name</label>
                            <input required type="text" name="lastNameTB" class="form-control" id="exampleInputPassword1" placeholder="<%= dentist.getLastName()%>">
                        </div>
                        <div class="form-group col-lg-6">
                            <label for="exampleInputPassword1">Email</label>
                            <input required type="text" name="emailTB" class="form-control" id="exampleInputPassword1" placeholder="<%= dentist.getEmail()%>">
                        </div>
                        <div class="form-group col-lg-6">
                            <label for="exampleInputPassword1">Password</label>
                            <input required type="password" name="passwordTB" class="form-control" id="exampleInputPassword1" placeholder="Enter Your New or Existing Password">
                        </div>
                        </div>


                        
                        <div class="form-group">
                            <label for="exampleInputPassword1">Office Number</label>
                            <input type="text" name="officeNumberTB" class="form-control" id="exampleInputPassword1" 
                                   placeholder="<%= dentist.getOffice()%>">
                        </div>
                        

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>


                </div>
            </div>
        </div>


        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>
