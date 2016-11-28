
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
<div class="row">
    <div class="col-md-12">
        <h1>Register</h1>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
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
            
            <div class="form-group">
                <label for="address">Address</label>
                <input class="form-control" id="address" name="address" type="text">
                </select>
            </div>
            <div class="form-group">
                <label for="addressLookup">Or</label>
                <button id="addressLookup" class="btn btn-info">Lookup Address</button>
            </div>
            <div id="addressLookupSection" style="display: none;">
            <div class="form-group">
                <label for="postCode">Enter Postcode</label>
                <input type="text" name="postCode" class="form-control" id="postCode" placeholder="">
            </div>
            <div class="form-group">
                <button id="addressLookupBtn" class="btn btn-success">Lookup</button>
            </div>
            <div class="form-group">
                <label for="selectAddress">Select Address</label>
                <select class="form-control" id="selectAddress" name="selectAddress">
                </select>
            </div>
            </div>
            <div class="form-group">
                <label for="dob">Date of Birth (dd-MM-yyyy)</label>
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
            <button style="width: 100%;" type="submit" class="btn btn-default">Register</button>
        </form>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <p id="registerErrorMessage" class="app-error-box">
            ${registerResponse.get("message")}
        </p>
    </div>
</div>
<!-- page content end -->

<!-- page script -->
<script>
    $(document).ready(function () {

        $('#dob').datepicker({
            dateFormat: 'dd-mm-yy', // set the date format
            changeYear: true, // show the year select drop down
            changeMonth: true, // show the month select drop down
            yearRange: "-100:+0" // set year range to the past 100 years
        });

        $('#addressLookupBtn').click(function (event) {

            event.preventDefault();

            $.ajax({
                type: 'POST',
                url: 'address-lookup',
                data: {postcode: $('#postCode').val().replace(" ", "")},
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    if (data.status !== "failure") {
                        var html = "";
                        var addresses = data.Addresses;
                        for (var i = 0; i < addresses.length; i++) {
                            html += '<option value="' + addresses[i] + '">' + addresses[i] + '</option>';
                        }
                        $('#selectAddress').html(html);
                    } else {
                        alert("Please enter a valid postcode");
                    }
                }
            });
        });
        
        $('#selectAddress').change(function (event) {
            event.preventDefault();
            $('#address').val($('#selectAddress').val() + ", " + $('#postCode').val());
            $('#addressLookupSection').hide();
            $('#postCode').val("");
            $('#selectAddress').html("");
        });
        
        $('#addressLookup').click(function (event) {
            event.preventDefault();
            $('#addressLookupSection').show();
        });
    });
</script>
<!-- page script end -->

<!-- include footer start (leave alone) -->
<jsp:include page='footer.jsp'/>
<!-- include header end -->
