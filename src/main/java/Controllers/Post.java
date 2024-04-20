package Controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/posts/add", "/posts/delete", "/posts/like", "/posts/unlike", "/myposts", "/follows",
        "/likes",
        "/posts" })
public class Post extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Models.User user = (Models.User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(getServletContext().getContextPath() + "/login");
        } else {
            String action = req.getServletPath();
            if (action.equals("/posts/add")) {
                String title = req.getParameter("title");
                String body = req.getParameter("content");

                if (title != null && body != null) {
                    int result = Services.Post.addPost(title, body, user.getId());

                    if (result > 0) {
                        List<Models.Post> allPosts = Services.Post.getAllPosts();
                        List<Models.Post> myPosts = Services.Post.getPostsByUser(user.getId());

                        session.setAttribute("allPosts", allPosts);
                        session.setAttribute("myPosts", myPosts);

                        resp.sendRedirect(getServletContext().getContextPath() + "/posts.jsp");
                    }
                } else {
                    resp.sendRedirect(getServletContext().getContextPath() + "/add-post.jsp");
                }

            } else if (action.equals("/posts/delete")) {
                Integer postId = Integer.parseInt(req.getParameter("id"));
                String confirmed = req.getParameter("confirmed");
                Models.Post toBeDeletedPost = (Models.Post) Services.Post.getPostById(postId);
                if (toBeDeletedPost.getUser().getId() == user.getId()) {
                    if (confirmed == null && postId != null) {
                        session.setAttribute("toBeDeletedPost", toBeDeletedPost);
                        resp.sendRedirect(getServletContext().getContextPath() + "/delete-post.jsp");
                    } else if (confirmed != null && confirmed.equals("yes") && postId != null) {
                        Services.Post.removePost(postId);
                        resp.sendRedirect(getServletContext().getContextPath() + "/posts");
                    } else {
                        resp.sendRedirect(getServletContext().getContextPath() + "/posts");
                    }
                } else {
                    resp.sendRedirect(getServletContext().getContextPath() + "/posts");
                }
            } else if (action.equals("/posts/like")) {
                Integer toBeLikedPostId = Integer.parseInt(req.getParameter("id"));
                Services.Like.likePost(user.getId(), toBeLikedPostId);
                resp.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            } else if (action.equals("/posts/unlike")) {
                Integer toBeUnLikedPostId = Integer.parseInt(req.getParameter("id"));
                Services.Like.unLikePost(user.getId(), toBeUnLikedPostId);
                resp.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            } else if (action.equals("/myposts")) {
                List<Models.Post> myPosts = Services.Post.getPostsByUser(user.getId());
                session.setAttribute("myPosts", myPosts);
                resp.sendRedirect(getServletContext().getContextPath() + "/mine.jsp");
            } else if (action.equals("/follows")) {
                List<Models.Post> follows = Services.Follow.getAllPostsOfFollowedUser(user.getId());
                session.setAttribute("follows", follows);
                resp.sendRedirect(getServletContext().getContextPath() + "/follows.jsp");
            } else if (action.equals("/likes")) {
                List<Models.Post> likes = Services.Like.getAllPostsILiked(user.getId());
                session.setAttribute("likes", likes);
                resp.sendRedirect(getServletContext().getContextPath() + "/likes.jsp");
            } else {
                List<Models.Post> allPosts;
                String userId = req.getParameter("uId");
                if (userId != null) {
                    allPosts = Services.Post.getPostsByUser(Integer.parseInt(userId));
                } else {
                    allPosts = Services.Post.getAllPosts();
                }

                session.setAttribute("allPosts", allPosts);

                resp.sendRedirect("posts.jsp");
            }
        }

    }
}