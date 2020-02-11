package mate.academy.internetshop.controller.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.UserService;
import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/Servlet/Users")
public class ShowAllUsersController extends HttpServlet {
    @Inject
    private static UserService userService;
    private static final Logger LOGGER = Logger.getLogger(ShowAllUsersController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("users", userService.getAllUsers());
        } catch (DataProcessingException e) {
            req.setAttribute("Msg", e);
            LOGGER.error(e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/views/showAllUsers.jsp").forward(req, resp);
    }
}
