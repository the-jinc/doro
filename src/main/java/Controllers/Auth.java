package Controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/login", "/register", "/logout" })
public class Auth extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getServletPath();
        HttpSession session = req.getSession();
        Models.User user = (Models.User) session.getAttribute("user");

        if (action.equals("/login")) {
            if (user == null) {
                String username = req.getParameter("username");
                String password = req.getParameter("password");

                if (username != null && password != null) {
                    user = Services.User.getUserByUsername(username);
                }

                try {
                    if (user != null && Models.User.checkPassword(password, user.getPassword())) {
                        session.setAttribute("user", user);
                        resp.sendRedirect("index.jsp");
                    } else {
                        resp.sendRedirect("login.jsp");
                    }
                } catch (Exception ex) {
                    resp.sendRedirect("login.jsp");
                }
            } else {
                resp.sendRedirect("index.jsp");
            }
        } else if (action.equals("/register")) {
            if (user == null) {
                String firstName = req.getParameter("firstname");
                String lastName = req.getParameter("lastname");
                String username = req.getParameter("username");
                String password = req.getParameter("password");

                if (firstName != null && lastName != null && password != null && username != null) {
                    int result = Services.User.addUser(firstName, lastName, username, password);

                    if (result > 0) {
                        session.setAttribute("user", user);
                        resp.sendRedirect("index.jsp");
                    }
                } else {
                    resp.sendRedirect("register.jsp");
                }
            } else {
                resp.sendRedirect("index.jsp");
            }

        } else if (action.equals("/logout")) {
            session.invalidate();
            resp.sendRedirect("index.jsp");
        }
    }
}
