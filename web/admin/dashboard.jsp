<%@page import="java.util.List"%>
<%@page import="com.esd.cw.model.User"%>
<!-- include header start (leave alone) -->
<jsp:include page='../header.jsp'/>
<!-- include header end -->

<!-- page content start (customise) -->
<h1>Admin Dashboard Page</h1>
<table class="table">
    <thead>
        <tr>
            <th>User ID</th>
            <th>Status</th>
            <th>Update</th>
        </tr>
    </thead>
    <tbody>
        <%
            for (User u : (List<User>) request.getAttribute("allUsers")) {
        %>
    <form role="form" method="post" action="manageuser">
        <tr>
            <td><input hidden name="userId" value="<%=u.getUserId()%>"><%=u.getUserId()%></td>
            <td><%=u.getUserStatus()%></td>
            <td><button class="btn btn-success">Manage</button></td>
        </tr>
    </form>
    <%
        }
    %>
</tbody>
</table>
<p>Page content here.</p>
<!-- page content end -->

<!-- include footer start (leave alone) -->
<jsp:include page='../footer.jsp'/>
<!-- include header end -->
