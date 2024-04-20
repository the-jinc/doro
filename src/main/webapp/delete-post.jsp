<%@ include file="no-cache.jsp" %> <%@ include file="auth.jsp" %> <%@ page
isELIgnored="false" %>
<html>
  <head>
    <%@ include file="header.jsp" %>
  </head>
  <body class="body--login">
    <div class="d-flex justify-content-center">
      <div
        class="card mt-3 me-3 ms-3 text-black"
        style="height: 100% !important; min-height: 100% !important"
      >
        <div class="card-header">Deleting a Post</div>
        <div class="card-body">
          <p class="card-text">Are you sure you want to delete this post?</p>
          <a
            href="posts/delete?id=${toBeDeletedPost.getId()}&confirmed=yes"
            class="btn btn-danger"
            >Delete</a
          >
          <a
            href="posts/delete?id=${toBeDeletedPost.getId()}&confirmed=no"
            class="btn btn-outline-primery"
            >Cancel</a
          >
        </div>
      </div>
    </div>
  </body>
</html>
