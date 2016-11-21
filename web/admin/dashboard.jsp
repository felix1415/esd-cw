<%@page import="java.util.List"%>
<%@page import="com.esd.cw.model.User"%>
<%@page import="com.esd.cw.dao.UserDao"%>
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
            UserDao userDao = new UserDao();
            for (User u : userDao.findAllNonAdminUsers()) {
        %>
    <form role="form" method="get" action="manageuser">
        <tr>
            <td><input hidden name="userId" value="<%=u.getUserId()%>"><%=u.getUserId()%></td>
            <td><%=u.getMember().getStatus()%></td>
            <td><button class="btn btn-success">Manage</button></td>
        </tr>
    </form>
    <%
        }
    %>
</tbody>
</table>
<h1>Charge All Users</h1>
    <form method="post" action="ChargeAllUsers">
        <button type="submit">Charge All Users</button>
    </form>
<br>

<div class="error-message">
    <%
    if (request.getAttribute("status") != null) {
        %>
        <%=request.getAttribute("status")%>
        <%
    }   
    %>
</div>
<p>Page content here.</p>
<!-- page content end -->

<!-- include footer start (leave alone) -->
<jsp:include page='../footer.jsp'/>
<!-- include header end -->
