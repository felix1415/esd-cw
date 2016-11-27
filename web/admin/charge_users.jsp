<!-- include header start (leave alone) -->
<jsp:include page='../header.jsp'/>
<!-- include header end -->

<div class="row">
    <div class="col-md-12">
        <h1>Charge Users</h1>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <div class="error-message">
            <%
                if (request.getAttribute("status") != null) {
            %>
            <%=request.getAttribute("status")%>
            <%
                }
            %>
        </div>
    </div>
</div>
