package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.OrderService;

public class ShowAllOrdersController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    private static final Long USER_ID = 1L;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("orders", orderService.getUserOrders(USER_ID));
        req.getRequestDispatcher("WEB-INF/views/ShowAllOrdersController").forward(req, resp);
    }
}
