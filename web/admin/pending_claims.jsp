<%@page import="com.esd.cw.model.Member"%>
<%@page import="com.esd.cw.model.User"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
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
            <th width='15%'>Member ID</th>
            <th width='15%'>Claim Date</th>
            <th width='20%'>Rationale</th>
            <th width='10%'>Amount</th>
            <th width='18%'>User's Remaining Claims</th>
            <th width='10%'>Action</th>
            <th width='12%'></th>
        </tr>
    </thead>
    <tbody>
        <%
            HashMap<String,List<Object>>  claimsAndUserMap = (HashMap<String,List<Object>>)request.getAttribute("pendingClaims");
            if(claimsAndUserMap.size() > 0){
            Iterator it = claimsAndUserMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                String userName = (String) pair.getKey();
                List<Object> userAndClaim = (List<Object>) pair.getValue();
                User user = (User) userAndClaim.get(0);
                Member member = user.getMember();
                Claim claim = (Claim)userAndClaim.get(1);
                            
        %>
    <form role="form" method="post" action="PendingClaims">
        <tr>
            <input hidden name="claimId" value="<%=claim.getId()%>">           
            <td><%=claim.getMemberId()%></td>
            <td><%=claim.getClaimDate()%></td>
            <td><%=claim.getRationale()%></td>
            <td><%=claim.getAmount()%></td>
            <td><%=member.getClaimsRemaining()%></td>
            <td>
                <select name="action">
                    <option value="accept">Accept</option>                    
                    <option value="reject">Reject</option>
                </select>
            </td>
            <td><button class="btn btn-success">Update</button></td>
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