<%@ include file="no-cache.jsp" %> <% if(session.getAttribute("user") != null) {
response.sendRedirect("index.jsp"); }%>
<html>
  <head>
    <%@ include file="header.jsp" %>
  </head>
  <body class="container body--login">
    <div class="row d-flex justify-content-center align-items-center vh-100">
      <div class="d-none d-md-block col-md-7 col-lg-8 col-xl-8">
        <img src="images/logo.png" class="img-fluid" alt="logo image" />
      </div>
      <form
        action="register"
        method="post"
        class="col-md-5 col-lg-4 col-xl-4 d-flex flex-column justify-content-center align-items-center gap-3 rounded p-4"
      >
        <h1 class="mt-4 mb-2 text-dark h3">
          Register <span class="bg-dark text-white p-1 rounded">Here</span>
        </h1>
        <div class="form-floating mb-3 w-100">
          <input
            type="text"
            class="form-control"
            id="firstname"
            name="firstname"
            placeholder="firstname"
          />
          <label for="firstname">First Name</label>
        </div>
        <div class="form-floating mb-3 w-100">
          <input
            type="text"
            class="form-control"
            id="lastname"
            name="lastname"
            placeholder="lastname"
          />
          <label for="lastname">Last Name</label>
        </div>
        <div class="form-floating mb-3 w-100">
          <input
            type="text"
            class="form-control"
            id="username"
            name="username"
            placeholder="username"
          />
          <label for="username">Username</label>
        </div>
        <div class="form-floating mb-3 w-100">
          <input
            type="password"
            class="form-control"
            id="password"
            name="password"
            placeholder="password"
          />
          <label for="password">Password</label>
        </div>
        <div class="mb-3">
          <p>already have an account? <a href="login.jsp">login here</a></p>
        </div>
        <button type="submit" class="btn btn-primary w-50">
          <i class="fa-solid fa-right-to-bracket"></i> Register
        </button>
      </form>
    </div>
  </body>
</html>
