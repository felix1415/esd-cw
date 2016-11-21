<%@page import="com.esd.cw.services.ClaimService"%>
<%@page import="com.esd.cw.model.User"%>
<!-- include header start (leave alone) -->
<jsp:include page='header.jsp'/>
<!-- include header end -->
<!-- page content start (customise) -->
<%
User user = (User) session.getAttribute("user");
ClaimService cService = new ClaimService ();
%>
<h1>Paid Member Dashboard</h1>
<div class="row">
    <div class="col-md-12">
        <div class="user-information-section">
            <div class="user-information-block">
                <label class="information-title">User ID</label>
                <div class="information-value"><%=user.getUserId()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Name</label>
                <div class="information-value"><%=user.getMember().getName()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Address</label>
                <div class="information-value"><%=user.getMember().getAddress()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Date of Birth</label>
                <div class="information-value"><%=user.getMember().getDateOfBirth()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Date of Registration</label>
                <div class="information-value"><%=user.getMember().getDateOfRegistration()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Status</label>
                <div class="information-value"><%=user.getMember().getStatus()%></div>
                <div class="user-information-block">
                <label class="information-title">Claim Status</label>
                <div class="information-value"><%=cService.claimStatus(user)%></div>
            </div>
        </div>
    </div>
</div>
<br>
<a href='claim'>Make Claim</a>
<!-- page content end -->

<!-- include footer start (leave alone) -->
<jsp:include page='footer.jsp'/>
<!-- include header end -->
