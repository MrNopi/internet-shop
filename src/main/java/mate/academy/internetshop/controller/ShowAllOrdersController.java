package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.services.OrderService;

public class ShowAllOrdersController extends HttpServlet {
    private static OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("orders", orderService.getUserOrders(1L));
        req.getRequestDispatcher("WEB-INF/views/ShowAllOrdersController").forward(req, resp);
    }
}
