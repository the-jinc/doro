<c:set var="rootPath" value="<%= request.getContextPath() %>" />
<c:set var="requestPath" value="<%= request.getRequestURI() %>" />
<c:set var="indexPath" value="${rootPath}/posts.jsp" />
<c:set var="followsPath" value="${rootPath}/follows.jsp" />
<c:set var="likesPath" value="${rootPath}/likes.jsp" />
<c:set var="minePath" value="${rootPath}/mine.jsp" />

<div class="col profile-col">
  <!-- Left column -->
  <div class="profile-header">
    <h3>${user.getFirstName()} ${user.getLastName()}</h3>
    <p>@${user.getUsername()}</p>
    <a
      href="add-post.jsp"
      type="button"
      class="btn btn-primary align-self-end mb-2"
      >Add Post</a
    >
    <form action="logout" method="post" style="margin: 0">
      <button
        type="submit"
        class="btn btn-warning text-nowrap"
        style="max-height: 40px"
      >
        <i class="fa-solid fa-right-from-bracket"></i> Logout
      </button>
    </form>
    <hr />

    <ul class="nav nav-pills flex-column gap-2">
      <li class="nav-item">
        <a
          href="index.jsp"
          class="nav-link link-body-emphasis ${requestPath == indexPath ? ' active': ''}"
        >
          <i class="me-2 fa-solid fa-house"></i>
          Home
        </a>
      </li>
      <li>
        <a
          href="follows"
          class="nav-link link-body-emphasis ${requestPath == followsPath ? ' active': ''}"
        >
          <i class="me-2 fa-solid fa-heart-circle-plus"></i>
          Follows
        </a>
      </li>
      <li>
        <a
          href="likes"
          class="nav-link link-body-emphasis ${requestPath == likesPath ? ' active': ''}"
        >
          <i class="me-2 fa-solid fa-thumbs-up"></i>
          Likes
        </a>
      </li>
      <li>
        <a
          href="myposts"
          class="nav-link link-body-emphasis ${requestPath == minePath ? ' active': ''}"
        >
          <i class="me-2 fa-solid fa-user-tie"></i>
          My posts
        </a>
      </li>
    </ul>
  </div>
</div>
