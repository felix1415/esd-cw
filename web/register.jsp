
<!-- include header start (leave alone) -->
<jsp:include page='header.jsp'/>
<!-- include header end -->

<%@ page import="java.util.*" %>

<%
    // get the registration response if we have one. to prefill form.
    if (request.getAttribute("registerResponse") != null) {
        Map registerResponse = (Map) request.getAttribute("registerResponse");   
    }
%>

<!-- page content start (customise) -->
<h1>Register</h1>

<br>
<form action="register" method="post">
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" name="username" class="form-control" id="username" placeholder="" value="${registerResponse.get("username")}">
    </div>
    <div class="form-group">
        <label for="firstName">First Name</label>
        <input type="text" name="firstName" class="form-control" id="firstName" placeholder="" value="${registerResponse.get("firstName")}">
    </div>
    <div class="form-group">
        <label for="lastName">Last Name</label>
        <input type="text" name="lastName" class="form-control" id="lastName" placeholder="" value="${registerResponse.get("lastName")}">
    </div>
    <!--
    <div class="form-group">
        <label for="postCode">Postcode</label>
        <input type="text" name="postCode" class="form-control" id="postCode" placeholder="">
    </div>
    <div class="form-group">
        <button id="addressLookupBtn" class="btn btn-default">Lookup</button>
    </div>
    -->
    <div class="form-group">
        <label for="address">Address</label>
        <input type="text" name="address" class="form-control" id="address" placeholder="" value="${registerResponse.get("address")}">
    </div>
    <div class="form-group">
        <label for="dob">Date of Birth</label>
        <input type="text" name="dob" class="form-control" id="dob" placeholder="" value="${registerResponse.get("dob")}">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" name="password" class="form-control" id="password" placeholder="">
    </div>
    <div class="form-group">
        <label for="password">Confirm Password</label>
        <input type="password" name="confirmPassword" class="form-control" id="confirmPassword" placeholder="">
    </div>
    <button type="submit" class="btn btn-default">Register</button>
</form>
<br>
<span id="registerErrorMessage" class="app-error-box">
    ${registerResponse.get("message")}
</span>
<!-- page content end -->

<!-- page script -->
<script>
    $(document).ready(function () {

        // address lookup
        $('#addressLookupBtn').click(function (event) {

            // prevent default action
            event.preventDefault();

            // check we have a value for postocde & address
            var postCode = $('#postCode').val();
            if (postCode === '') {
                alert('Please enter a valid postcode');
            } else {

                // ajax for address
                $.ajax({
                    type: "POST",
                    url: "https://api.getaddress.io/v2/uk/sw1a2aa?api-key=D9jCYZtwtk-4l82c6Yo0uw6192",
                    dataType: "json",
                    success: function (response) {
                        console.log(response);
                    }
                });
            }

        });
    });
</script>
<!-- page script end -->

<!-- include footer start (leave alone) -->
<jsp:include page='footer.jsp'/>
<!-- include header end -->
