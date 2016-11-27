<%@page import="java.util.Iterator"%>
<%@page import="com.esd.cw.model.Claim"%>
<%@page import="com.esd.cw.model.Payment"%>
<%@page import="java.util.List"%>
<%@page import="com.esd.cw.services.ClaimService"%>
<%@page import="com.esd.cw.model.User"%>
<!-- include header start (leave alone) -->
<jsp:include page='header.jsp'/>
<!-- include header end -->
<!-- page content start (customise) -->
<%
    User user = (User) session.getAttribute("user");
    ClaimService cService = new ClaimService();
    List<Payment> payment = (List) request.getAttribute("payments");
    List<Claim> claim = (List) request.getAttribute("claims");%>
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
    <div class ="user-information-block">
        <div class="information-title" > <br>
            <label class="information-title">Payments</label>
            <table class="table">
                <thead>
                    <tr>
                        <th>Type</th>
                        <th>Date</th>
                        <th>Amount</th>
                    </tr>
                </thead>
                <tbody>
                    <%

                        for (Payment payment1 : payment) {

                            String date = payment1.getDateOfPayment().toString();
                            String amount = Float.toString(payment1.getPaymentAmount());
                            String type = payment1.getTypeOfPayment();
                    %>

                    <tr>
                        <td><%=type%></td>
                        <td><%=date%></td>
                        <td><%=amount%></td>
                    </tr>

                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>

    <div class ="user-information-block">
        <div class="information-title" > <br>
            <label class="information-title">Claims</label>
            <table class="table">
                <thead>
                    <tr>
                        <th>rationale</th>
                        <th>Date</th>
                        <th>Amount</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (Claim claim1 : claim) {

                            String date = claim1.getClaimDate().toString();
                            String amount = Float.toString(claim1.getAmount());
                            String rationale = claim1.getRationale();
                            String status = claim1.getStatus();
                    %>

                    <tr>
                        <td><%=rationale%></td>
                        <td><%=date%></td>
                        <td><%=amount%></td>
                        <td><%=status%></td>
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
    <jsp:include page='footer.jsp'/>
    <!-- include header end -->
