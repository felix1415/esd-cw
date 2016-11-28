<%@page import="com.esd.cw.dao.ClaimDao"%>
<%@page import="com.esd.cw.model.Claim"%>
<%@page import="java.util.List"%>
<%@page import="com.esd.cw.model.User"%>
<%@page import="com.esd.cw.dao.UserDao"%>

<!-- include header start (leave alone) -->
<jsp:include page='../header.jsp'/>
<!-- include header end -->

<!-- page content start (customise) -->
<div class="row">
    <div class="col-md-12">
        <h1>Admin Dashboard Page</h1>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
        <h4>Filters</h4>
        <form role="form" method="get" class="form-inline">
            <div class="form-group">
                <label for="usernameFilter">Username </label>
                <input type="text" name="search" class="form-control" id="usernameFilter">
            </div>
            <button type="submit" class="btn btn-warning">Filter</button>
        </form>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <br>
        <form method="get" action="AdminReport">
            <button class="btn btn-default">Reports</button>
        </form>
        <br>
        <form method="post" action="ChargeAllUsers">
            <button class="btn btn-default" type="submit">Charge All Users</button>
        </form>
    </div>
</div>

<div class="row">
    <div class="col-md-12">
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
    </div>
</div>
<!-- page content end -->

<!-- include footer start (leave alone) -->
<jsp:include page='../footer.jsp'/>
<!-- include header end -->
