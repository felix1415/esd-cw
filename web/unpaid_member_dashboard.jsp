<!-- include header start (leave alone) -->
<jsp:include page='header.jsp'/>
<!-- include header end -->
<!-- page content start (customise) -->
<h1>Unpaid Member Dashboard</h1>
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
<!-- page content end -->

<!-- include footer start (leave alone) -->
<jsp:include page='footer.jsp'/>
<!-- include header end -->
