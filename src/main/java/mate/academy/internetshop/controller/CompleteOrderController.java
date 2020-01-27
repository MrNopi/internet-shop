package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.OrderService;
import mate.academy.internetshop.services.UserService;

@WebServlet(urlPatterns = "/Servlet/CompleteOrder")
public class CompleteOrderController extends HttpServlet {

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
        Long userId = (Long)req.getSession().getAttribute("userId");
        try {
            orderService.completeOrder(bucketService.get(userId).getItems(),
                    userService.get(userId));
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.setAttribute("orders", orderService.getUserOrders(userId));
        req.getRequestDispatcher("/WEB-INF/views/ShowAllOrders.jsp").forward(req, resp);
    }
}
