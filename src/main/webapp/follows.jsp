<%@ include file="no-cache.jsp" %> <%@ include file="auth.jsp" %>
<html>
  <head>
    <%@ include file="header.jsp" %>
  </head>
  <body class="body--login">
    <div class="container main-content">
      <div class="row">
        <%@ include file="sidebar.jsp" %>

        <!-- Center content column -->
        <div class="col-6">
          <ol class="tweet-list">
            <c:if test="${follows != null && follows.size() != 0}">
              <c:forEach var="i" begin="0" end="${follows.size() - 1}">
                <li class="tweet-card">
                  <div class="tweet-content">
                    <div class="tweet-header">
                      <span class="fullname">
                        <strong
                          >${follows.get(i).getUser().getFirstName()}
                          ${follows.get(i).getUser().getLastName()}</strong
                        >
                      </span>
                      <span class="username"
                        >@${follows.get(i).getUser().getUsername()}</span
                      >
                      <c:if
                        test="${follows.get(i).getUser().getId() != user.getId()}"
                      >
                        <span class="ms-3"
                          ><a
                            href="users/unfollow?id=${follows.get(i).getUser().getId()}"
                          >
                            <i class="fa fa-user-times"></i></a></span
                      ></c:if>
                    </div>

                    <div class="tweet-text">
                      <p class="" lang="es" data-aria-label-part="0">
                        <strong>${follows.get(i).getTitle()}</strong>
                        <br />${follows.get(i).getContent()}
                      </p>
                    </div>
                  </div>
                  <c:if
                    test="${follows.get(i).getUser().getId() != user.getId()}"
                    ><c:choose>
                      <c:when test="${likes != null && likes.size() != 0}">
                        <c:forEach var="j" begin="0" end="${likes.size() - 1}">
                          <c:choose>
                            <c:when
                              test="${follows.get(i).getId() != likes.get(j).getId()}"
                              ><span class="ms-3"
                                ><a
                                  href="posts/like?id=${follows.get(i).getId()}"
                                >
                                  <i class="fa fa-thumbs-up"></i></a></span
                            ></c:when>
                            <c:when
                              test="${follows.get(i).getId() == likes.get(j).getId()}"
                              ><span class="ms-3"
                                ><a
                                  href="posts/unlike?id=${follows.get(i).getId()}"
                                >
                                  <i class="fa fa-thumbs-down"></i></a></span
                            ></c:when>
                            <c:otherwise>undefined</c:otherwise>
                          </c:choose>
                        </c:forEach>
                      </c:when>
                      <c:otherwise>
                        <span class="ms-3"
                          ><a href="posts/like?id=${follows.get(i).getId()}">
                            <i class="fa fa-thumbs-up"></i></a
                        ></span>
                      </c:otherwise>
                    </c:choose>
                  </c:if>
                </li>
              </c:forEach>
            </c:if>
            <c:if test="${follows != null && follows.size() == 0}">
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
