package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Role;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.UserService;

@WebServlet("/Inject")
public class InjectDataController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        User adminUser = new User("Jeremy");
        adminUser.setLogin("admin");
        adminUser.setPassword("1");
        adminUser.addRole(new Role("ADMIN"));
        userService.create(adminUser);

        resp.sendRedirect(req.getContextPath() + "/registration");
    }
}
