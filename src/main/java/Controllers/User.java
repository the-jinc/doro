package Controllers;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/users/follow", "/users/unfollow" })
public class User extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Models.User user = (Models.User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("login.jsp");
        } else {
            String action = req.getServletPath();
            if (action.equals("/users/follow")) {
                Integer toBeFolledUserId = Integer.parseInt(req.getParameter("id"));
                Services.Follow.followUser(user.getId(), toBeFolledUserId);
                resp.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            } else if (action.equals("/users/unfollow")) {
                Integer toBeUnFolledUserId = Integer.parseInt(req.getParameter("id"));
                Services.Follow.unFollowUser(user.getId(), toBeUnFolledUserId);
                resp.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
            } else {
                ArrayList<Models.User> users = (ArrayList<Models.User>) Services.User.getAllUsers(user.getId());

                // List<Post> allPosts = PostModel.getAllPosts();
                // List<Post> myPosts = PostModel.getPostsByUser(user.getId());

                session.setAttribute("users", users);
                // session.setAttribute("allPosts", allPosts);
                // session.setAttribute("myPosts", myPosts);

                resp.sendRedirect("index.jsp");
            }
        }

    }
}