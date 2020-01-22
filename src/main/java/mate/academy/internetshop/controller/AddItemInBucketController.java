package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.ItemService;

@WebServlet(urlPatterns = "/Servlet/addToBucket")
public class AddItemInBucketController extends HttpServlet {
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long)req.getSession().getAttribute("userId");
        Long itemId = Long.valueOf(req.getParameter("itemId"));
        Bucket bucket = bucketService.get(userId);
        bucketService.addItem(bucket, itemService.get(itemId));
        resp.sendRedirect(req.getContextPath() + "/Servlet/ShowBucketItems");
    }
}
