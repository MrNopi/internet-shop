package mate.academy.internetshop.controller.Order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.controller.User.ShowAllUsersController;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.OrderService;
import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/Servlet/ShowAllOrders")
public class ShowAllOrdersController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    private static final Logger LOGGER = Logger.getLogger(ShowAllOrdersController.class);

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long)req.getSession().getAttribute("userId");
        try {
            req.setAttribute("orders", orderService.getUserOrders(userId));
        } catch (DataProcessingException e) {
            req.setAttribute("Msg", e);
            LOGGER.error(e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/views/ShowAllOrdersController").forward(req, resp);
    }
}
