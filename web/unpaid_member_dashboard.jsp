<!-- include header start (leave alone) -->
<jsp:include page='header.jsp'/>
<!-- include header end -->
<!-- page content start (customise) -->
<h1>Unpaid Member Dashboard</h1>
<p style="color: red;">As we have the user session we have access to the User model object. (which also, since i adjusted it has an attribute of the Member model. So you don't need to set attributes ect to access this data. Rather just user.getMember().getAddress() for example. Gimmi a shout when we are next working on it and i will shwo you can how can access this much more easily & cleanly (aidan))</p>
<br>
<br>
<span id="loginErrorMessage" class="app-error-box">
<%  out.println("Member ID: " + request.getAttribute("id")); %> <br/>
<%  out.println("Name: " + request.getAttribute("name")); %> <br/>
<%  out.println("Address: " + request.getAttribute("address")); %> <br/>
<%  out.println("Date Of Birth: " + request.getAttribute("dob")); %> <br/>
<%  out.println("Date Of Registration: " + request.getAttribute("dor")); %> <br/>
<%  out.println("Account Status: " + request.getAttribute("status")); %> <br/>
<%  out.println("Balance: " + request.getAttribute("balance")); %> <br/>
<%  out.println("Claims Remaining: " + request.getAttribute("claims_remaining")); %> <br/>
</span>
<a href='payment'>Make Payment</a>
<!-- page content end -->

<!-- include footer start (leave alone) -->
<jsp:include page='footer.jsp'/>
<!-- include header end -->
