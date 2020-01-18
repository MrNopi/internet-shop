package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.OrderService;

@WebServlet(urlPatterns = "/Servlet/RemoveOrder")
public class RemoveOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        orderService.delete(orderId);
        resp.sendRedirect(req.getContextPath() + "/Servlet/index");
    }
}
