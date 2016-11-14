<!-- include header start (leave alone) -->
<jsp:include page='header.jsp'/>
<!-- include header end -->
<c:set var="loginMessage" scope="session" value=""/>
<!-- page content start (customise) -->
<h1>Login</h1>
<br>
<form action="login" method="post">
  <div class="form-group">
    <label for="username">Username</label>
    <input type="text" name="username" class="form-control" id="username" placeholder="">
  </div>
  <div class="form-group">
    <label for="password">Password</label>
    <input type="password" name="password" class="form-control" id="password" placeholder="">
  </div>
  <button type="submit" class="btn btn-default">Login</button>
</form>
<br>
<span id="loginErrorMessage" class="app-error-box">
    
    <%
        if( request.getAttribute("loginMessage") != null )
        {
            out.print(request.getAttribute("loginMessage"));
        }
    %>
</span>
<!-- page content end -->

<!-- include footer start (leave alone) -->
<jsp:include page='footer.jsp'/>
<!-- include header end -->
