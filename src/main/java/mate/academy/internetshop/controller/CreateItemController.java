package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.services.ItemService;
import org.apache.log4j.Logger;

@WebServlet(urlPatterns = "/Servlet/createItem")
public class CreateItemController extends HttpServlet {
    @Inject
    private static ItemService itemService;
    private static final Logger LOGGER = Logger.getLogger(CreateItemController.class);

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        String itemName = req.getParameter("itemName");
        Double itemPrice = Double.valueOf(req.getParameter("itemPrice"));
        Item newItem = new Item(itemName).setPrice(itemPrice);
        try {
            itemService.create(newItem);
        } catch (DataProcessingException e) {
            req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
            LOGGER.error(e);
        }
        resp.sendRedirect(req.getContextPath() + "/Servlet/index");
    }
}
