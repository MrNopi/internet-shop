package mate.academy.internetshop.controller.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.OrderService;
import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/Servlet/RemoveOrder")
public class RemoveOrderController extends HttpServlet {
    @Inject
    private static OrderService orderService;
    private static final Logger LOGGER = Logger.getLogger(RemoveOrderController.class);

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        Long orderId = Long.valueOf(req.getParameter("orderId"));
        try {
            orderService.delete(orderId);
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.setAttribute("Msg", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/Servlet/index");
    }
}
