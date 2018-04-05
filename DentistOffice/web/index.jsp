<%@include file="Header.jsp" %>
<style>
    .jumbotron {
        background-image: url("http://drjoh.com/wp-content/uploads/2012/03/dental-implants-dentures-clinton-twp-mi-2.jpg");
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
        color: #353A3F;
        font-size: 5.2em;
    }
    

    .jumbotron h3{
        color: #353A3F;
        font-size: 3.0em;
    }

    #mainDiv{
        margin-top: 30px;
    }

    .inputDiv{
        padding: 10px;
        margin-left: 20px;
    }
    
    .radioChoice{
        display: flex;
        justify-content: center;
        padding: 20px;
        margin: 0 auto;
    }
    
    .radioChoice label{
        padding: 10px;
    }
    
    .formContainer{
        margin: 50px;
    }
</style>

<div class="jumbotron">
    <h1 class="display-4 text-center">Ear To Ear Dentistry</h1>
    <hr class="my-8">
    <h3 class="text-center">"Where you will leave smiling"</h3>
    <div class="overlay"></div>
</div>


<div id="mainDiv" class="container">
    <div class="row">
        <div class="col-lg-4">
            <div class="text-center">
                <img src="./assets/molar.svg" height="100"/>
            </div>
            <h3 class="text-center">Fillings</h3>
            <p class="text-center">Lorem ipsum dolor sit amet consectetur adipiscing elit ligula enim at habitasse vestibulum leo eros volutpat, aliquet primis tristique ultricies placerat malesuada vel erat vivamus blandit nostra donec nisi.</p>
        </div>
        <div class="col-lg-4">
            <div class="text-center">
                <img src="./assets/toothbrush.svg" height="100"/>
            </div>
            <h3 class="text-center">Cleaning</h3>
            <p class="text-center">Lorem ipsum dolor sit amet consectetur adipiscing elit ligula enim at habitasse vestibulum leo eros volutpat, aliquet primis tristique ultricies placerat malesuada vel erat vivamus blandit nostra donec nisi.</p>
        </div>
        <div class="col-lg-4">
            <div class="text-center">
                <img src="./assets/drill.svg" height="100"/>
            </div>
            <h3 class="text-center">Services</h3>
            <p class="text-center">Lorem ipsum dolor sit amet consectetur adipiscing elit ligula enim at habitasse vestibulum leo eros volutpat, aliquet primis tristique ultricies placerat malesuada vel erat vivamus blandit nostra donec nisi.</p>
        </div>
    </div>
    
    
    <div class="formContainer">
        <div>
        <h1 class="text-center">Log In</h1>
    </div>
        <form action="LoginServlet" method="POST">
            <div class="row">
                <div class="form-group col-lg-6">
                    <label for="exampleInputEmail1">Email address</label>
                    <input type="email" name="emailInput" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email" required>
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
                </div>


                <div class="form-group col-lg-6">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" name="passwordInput" class="form-control" id="exampleInputPassword1" placeholder="Password" required>
                </div>

            </div>
            <h4 class="text-center">Please Choose  An Account Type:</h4>
            <div class="btn-group radioChoice" data-toggle="buttons">

                <label class="btn btn-outline-primary">
                    <input type="radio" name="accountRadioBtn" id="option1" value="Patient"> Patient
                </label>
                <label class="btn btn-outline-primary">
                    <input type="radio" name="accountRadioBtn" id="option2" value="Dentist"> Dentist
                </label>

            </div>
            <button type="submit" class="btn btn-primary btn-block">Submit</button>
        </form>
    </div>

</div>

<%@include file="Footer.jsp" %>