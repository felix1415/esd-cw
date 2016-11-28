<%@page import="com.esd.cw.model.User"%>
<!-- include header start (leave me alone) -->
<jsp:include page='header.jsp'/>
<!-- include header end -->

<!-- page content start (customise) -->
<h1>Membership Payment Form</h1>
<a href="payment.jsp"></a>
<form action="payment" method="post">
    <div class="form-group">
        <label for="paymentAmount">Payment Amount</label>
        <input class="form-control" type="text" name="paymentAmount">
    </div>
    <input class="btn btn-success" type="submit" value="Make Payment">
    <div style="color: green;">
        <%
            if (request.getAttribute("paymentStatus") != null) {
                    out.print(request.getAttribute("paymentStatus"));
            }
        %>
    </div>
</form>
<!-- page content end -->

<!-- include footer start (leave me alone) -->
<jsp:include page='footer.jsp'/>
<!-- include header end -->
