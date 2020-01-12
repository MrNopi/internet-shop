package mate.academy.internetshop.controller;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.services.ItemService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateItemController extends HttpServlet {
    @Inject
    private static ItemService itemService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemName = req.getParameter("itemName");
        Double itemPrice = Double.valueOf(req.getParameter("itemPrice"));
        Item newItem = new Item(itemName).setPrice(itemPrice);
        itemService.create(newItem);
        resp.sendRedirect(req.getContextPath() + "/index");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getParameter("item");
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}