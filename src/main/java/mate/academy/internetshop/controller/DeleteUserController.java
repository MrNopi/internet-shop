package mate.academy.internetshop.controller;

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

@WebServlet(urlPatterns = "/Servlet/deleteUser")
public class DeleteUserController extends HttpServlet {
    @Inject
    private static UserService userService;
    private static final Logger LOGGER = Logger.getLogger(DeleteUserController.class);

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        try {
            userService.delete(userId);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.setAttribute("Msg", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/Servlet/index");
    }
}
