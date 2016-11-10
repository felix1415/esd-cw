<%@page import="com.esd.cw.model.Payment"%>
<%@page import="com.esd.cw.model.User"%>
<!-- include header start (leave alone) -->
<jsp:include page='../header.jsp'/>
<!-- include header end -->

<!-- page style -->
<style>
    .info-heading {
        color: green;
    }
    .user-information-section .user-information-block {
        display: block;
    }
    .information-value {
        margin-top: -5px;
        margin-bottom: 10px;
    }
</style>

<%
    // grab the user we are managing for use in the form / page
    User manageUser = (User) request.getAttribute("manageUser");
%>

<!-- page content start (customise) -->
<h1>Admin - Manage User</h1>
<h3 class="info-heading">User Information</h3>
<div class="row">
    <div class="col-md-12">
        <div class="user-information-section">
            <div class="user-information-block">
                <label class="information-title">User ID</label>
                <div class="information-value"><%=manageUser.getUserId()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Name</label>
                <div class="information-value"><%=manageUser.getMember().getName()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Address</label>
                <div class="information-value"><%=manageUser.getMember().getAddress()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Date of Birth</label>
                <div class="information-value"><%=manageUser.getMember().getDateOfBirth()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Date of Registration</label>
                <div class="information-value"><%=manageUser.getMember().getDateOfRegistration()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Status</label>
                <div class="information-value"><%=manageUser.getMember().getStatus()%></div>
            </div>
        </div>
    </div>
</div>
<h3 class="info-heading">Financial & Account Information</h3>
<div class="row">
    <div class="col-md-12">
        <div class="user-information-section">
            <div class="user-information-block">
                <label class="information-title">Claims Remaining</label>
                <div class="information-value"><%=manageUser.getMember().getClaimsRemaining()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Balance</label>
                <div class="information-value">&pound; <%=manageUser.getMember().getBalance()%></div>
            </div>
        </div>
    </div>
</div>
<h3 class="info-heading">Claims</h3>
<div class="row">
    <div class="col-md-12">
        TODO
    </div>
</div>
<h3 class="info-heading">Payment History</h3>
<div class="row">
    <div class="col-md-12">
        <table class="table">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Type</td>
                    <td>Amount</td>
                    <td>Date</td>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Payment payment : manageUser.getPayments()) {
                %>
                <tr>
                    <td><%=payment.getPaymentId()%></td>
                    <td><%=payment.getTypeOfPayment()%></td>
                    <td><%=payment.getPaymentAmount()%></td>
                    <td><%=payment.getDateOfPayment()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>
<!-- page content end -->

<!-- include footer start (leave alone) -->
<jsp:include page='../footer.jsp'/>
<!-- include header end -->
