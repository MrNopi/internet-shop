package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mate.academy.internetshop.exception.AuthorisationException;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.UserService;
import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;
    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        try {
            User user = null;
            try {
                user = userService.login(login, password);
            } catch (DataProcessingException e) {
                req.setAttribute("Msg", e);
                LOGGER.error(e);
                req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            }
            HttpSession session = req.getSession(true);
            session.setAttribute("token", user.getToken());
            resp.sendRedirect(req.getContextPath() + "/Servlet/index");
        } catch (AuthorisationException e) {
            req.setAttribute("errorMsg", "Incorrect username or password");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
        }
    }
}
