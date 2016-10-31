<!-- include header start (leave alone) -->
<jsp:include page='header.jsp'/>
<!-- include header end -->

<%@ page import="java.util.*" %>

<!-- page content start (customise) -->
<h1>Register Page</h1>
<br>
<form action="register" method="post">
    <div class="form-group">
        <label for="username">Username</label>
        <input type="text" name="username" class="form-control" id="username" placeholder="" value="">
    </div>
    <div class="form-group">
        <label for="firstName">First Name</label>
        <input type="text" name="firstName" class="form-control" id="firstName" placeholder="">
    </div>
    <div class="form-group">
        <label for="lastName">Last Name</label>
        <input type="text" name="lastName" class="form-control" id="lastName" placeholder="">
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
        <input type="text" name="address" class="form-control" id="address" placeholder="">
    </div>
    <div class="form-group">
        <label for="dob">Date of Birth</label>
        <input type="text" name="dob" class="form-control" id="dob" placeholder="">
    </div>
    <div class="form-group">
        <label for="password">Password</label>
        <input type="password" name="password" class="form-control" id="password" placeholder="">
    </div>
    <button type="submit" class="btn btn-default">Register</button>
</form>
<br>
<span id="registerErrorMessage" class="app-error-box">
    Any error message here...
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
