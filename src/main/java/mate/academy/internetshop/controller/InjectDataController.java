package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Role;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.UserService;
import org.apache.log4j.Logger;

@WebServlet("/Inject")
public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;
    private static final Logger LOGGER = Logger.getLogger(InjectDataController.class);

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        User adminUser = new User("Jeremy");
        adminUser.setLogin("admin");
        adminUser.setPassword("1");
        adminUser.addRole(new Role("ADMIN"));
        try {
            userService.create(adminUser);
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            LOGGER.error(e);
        }

        resp.sendRedirect(req.getContextPath() + "/registration");
    }
}
