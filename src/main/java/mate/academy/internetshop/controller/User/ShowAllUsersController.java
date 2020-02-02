package mate.academy.internetshop.controller.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.UserService;

@WebServlet(urlPatterns = "/Servlet/Users")
public class ShowAllUsersController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("users", userService.getAllUsers());
        req.getRequestDispatcher("/WEB-INF/views/showAllUsers.jsp").forward(req, resp);
    }
}
