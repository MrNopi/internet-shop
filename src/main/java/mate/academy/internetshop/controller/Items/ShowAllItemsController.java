package mate.academy.internetshop.controller.Items;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.services.ItemService;
import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/Servlet/Items")
public class ShowAllItemsController extends HttpServlet {
    @Inject
    private static ItemService itemService;
    private static final Logger LOGGER = Logger.getLogger(ShowAllItemsController.class);

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            req.setAttribute("items", itemService.getAllItems());
        } catch (DataProcessingException e) {
            LOGGER.error(e);
            req.setAttribute("Msg", e);
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
        }
        req.getRequestDispatcher("/WEB-INF/views/showAllItems.jsp").forward(req, resp);
    }
}
