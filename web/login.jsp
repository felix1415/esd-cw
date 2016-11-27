<!-- include header start (leave alone) -->
<jsp:include page='header.jsp'/>
<!-- include header end -->
<c:set var="loginMessage" scope="session" value=""/>
<!-- page content start (customise) -->
<div class="row">
    <div class="col-md-12">
        <h1>Login</h1>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <form action="login" method="post">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" name="username" class="form-control" id="username" placeholder="">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" name="password" class="form-control" id="password" placeholder="">
            </div>
            <button style="width: 100%;" type="submit" class="btn btn-default">Login</button>
        </form>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <p id="loginErrorMessage" class="app-error-box">
            <%
                if (request.getAttribute("loginMessage") != null) {
                    out.print(request.getAttribute("loginMessage"));
                }
            %>
        </p>
    </div>
</div>
<!-- page content end -->

<!-- include footer start (leave alone) -->
<jsp:include page='footer.jsp'/>
<!-- include header end -->
