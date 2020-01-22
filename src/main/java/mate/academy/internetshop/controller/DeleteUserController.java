package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.UserService;

@WebServlet(urlPatterns = "/Servlet/deleteUser")
public class DeleteUserController extends HttpServlet {
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        userService.delete(userId);
        resp.sendRedirect(req.getContextPath() + "/Servlet/index");
    }
}
