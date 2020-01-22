package mate.academy.internetshop.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/Servlet/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
            req.getSession().invalidate();
        req.getRequestDispatcher("WEB-INF/views/login.jsp").forward(req, resp);
    }
}
