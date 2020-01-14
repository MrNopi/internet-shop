package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.OrderService;
import mate.academy.internetshop.services.UserService;

public class CompleteOrderController extends HttpServlet {
    private static final Long USER_ID = 1L;

    @Inject
    private static OrderService orderService;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static UserService userService;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        orderService.completeOrder(bucketService.get(USER_ID).getAllItems(),
                userService.get(USER_ID));
        req.setAttribute("orders", orderService.getUserOrders(USER_ID));
        req.getRequestDispatcher("WEB-INF/views/ShowAllOrders.jsp").forward(req, resp);
    }
}
