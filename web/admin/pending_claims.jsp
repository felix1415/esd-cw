<%@page import="java.util.ArrayList"%>
<%@page import="com.esd.cw.model.Claim"%>
<!-- include header start (leave me alone) -->
<jsp:include page='../header.jsp'/>
<!-- include header end -->

<!-- page content start (customise) -->
<h1>Pending Claims</h1>
    <table class="table">
    <thead>
        <tr>
            <th>Member ID</th>
            <th>Claim Date</th>
            <th>Rationale</th>
            <th>Amount</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <%
            ArrayList<Claim> claims = (ArrayList<Claim>)request.getAttribute("pendingClaims");
            if(claims.size() > 0){
            for (Claim claim : claims ) {
        %>
    <form role="form" method="post" action="PendingClaims">
        <tr>
            <input hidden name="claimId" value="<%=claim.getId()%>">           
            <td><%=claim.getMemberId()%></td>
            <td><%=claim.getClaimDate()%></td>
            <td><%=claim.getRationale()%></td>
            <td><%=claim.getAmount()%></td>
            <td><button class="btn btn-success">Accept Claim</button></td>
        </tr>
    </form>
    <%
            }
        }else{
    %>
    <h3>There are no pending claims</h3>
    <%
        }
        %>
</tbody>
</table>
<!-- page content end -->

<!-- include footer start (leave me alone) -->
<jsp:include page='../footer.jsp'/>
<!-- include header end -->