<!-- include header start (leave alone) -->
<jsp:include page='../header.jsp'/>
<!-- include header end -->

<!-- page content start (customise) -->

<%
    double totalClaimsInPastYear = (double)session.getAttribute("totalClaimsInPastYear");
    double totalPaymentsInPastYear = (double)session.getAttribute("totalPaymentsInPastYear");
%>
<h1>Admin Report</h1>
<div class="user-information-block">
    <label class="information-title">Claims in past year</label>
    <div class="information-value"><%=String.format("%.2f", totalClaimsInPastYear)%></div>
</div>
<div class="user-information-block">
    <label class="information-title">Payments in the past year</label>
    <div class="information-value"><%=String.format("%.2f", totalPaymentsInPastYear)%></div>
</div>
<!-- page content end -->

<!-- include footer start (leave alone) -->
<jsp:include page='../footer.jsp'/>
<!-- include header end -->
