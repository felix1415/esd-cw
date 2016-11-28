<%@page import="com.esd.cw.model.User"%>
<!-- include header start (leave me alone) -->
<jsp:include page='header.jsp'/>
<!-- include header end -->

<!-- page content start (customise) -->
<h1>Payment form</h1>
<a href="payment.jsp"></a>
<form action="payment" method="post">
    <div class="form-group">
        <label for="paymentAmount">Payment Amount</label>
        <input class="form-control" type="text" name="paymentAmount">
    </div>
    <div class="form-group">
        <label for="paymentType">Payment Type</label>
        <select class="form-control" name="paymentType" id="paymentType">
            <option>Membership</option>
            <option selected="selected">Donation</option>
        </select>
    </div>
    <input class="btn btn-success" type="submit" value="Make Payment">
    <div style="color: red;">
        <%=request.getAttribute("paymentStatus")%>
    </div>
</form>
<!-- page content end -->

<!-- include footer start (leave me alone) -->
<jsp:include page='footer.jsp'/>
<!-- include header end -->