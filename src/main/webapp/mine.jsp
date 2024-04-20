<%@ include file="no-cache.jsp" %> <%@ include file="auth.jsp" %> <%@ page
isELIgnored="false" %>
<html>
  <head>
    <%@ include file="header.jsp" %>
  </head>
  <body class="body--login">
    <div class="container main-content">
      <div class="row">
        <%@ include file="sidebar.jsp" %>
        <div class="col-6">
          <ol class="tweet-list">
            <c:if test="${myPosts != null && myPosts.size() != 0}">
              <c:forEach var="i" begin="0" end="${myPosts.size() - 1}">
                <li class="tweet-card">
                  <div class="tweet-content">
                    <div class="tweet-header">
                      <span class="fullname">
                        <strong
                          >${myPosts.get(i).getUser().getFirstName()}
                          ${myPosts.get(i).getUser().getLastName()}</strong
                        >
                      </span>
                      <span class="username"
                        >@${myPosts.get(i).getUser().getUsername()}</span
                      >
                    </div>

                    <div class="tweet-text">
                      <p class="" lang="es" data-aria-label-part="0">
                        <strong>${myPosts.get(i).getTitle()}</strong>
                        <br />${myPosts.get(i).getContent()}
                      </p>
                    </div>
                  </div>
                  <a
                    href="posts/delete?id=${myPosts.get(i).getId()}"
                    class="card-link btn btn-danger"
                    >Delete</a
                  >
                </li>
              </c:forEach>
            </c:if>
            <c:if test="${myPosts != null && myPosts.size() == 0}">
              <div colspan="5" class="text-center text-black mt-5">
                No posts to display
              </div>
            </c:if>
          </ol>
          <!-- End: tweet list -->
        </div>
        <!-- End: Center content column -->
        <div class="col right-col">
          <div class="content-panel"></div>
        </div>
      </div>
    </div>
  </body>
</html>
