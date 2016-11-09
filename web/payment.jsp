<%@page import="com.esd.cw.model.User"%>
<!-- include header start (leave me alone) -->
<jsp:include page='header.jsp'/>
<!-- include header end -->

<!-- page content start (customise) -->

<br>
<h1>Payment form</h1>
<a href="payment.jsp"></a>
<form action="payment" method="post">

    <div>Amount <input type="text" name="paymentAmount"></div>

    <div>Payment type 

        <select name="paymentType">
            <option></option>
            <option>Membership</option>
            <option selected="selected">Claim????</option>

        </select>

    </div>


    <input type="submit" value="submit">
    
    <div>
        <% out.print(request.getAttribute("paymentStatus"));%>
    </div>


</form>
Don't edit this page. Use it as a template to base other pages from.
<!-- page content end -->

<!-- include footer start (leave me alone) -->
<jsp:include page='footer.jsp'/>
<!-- include header end -->