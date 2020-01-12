package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.ItemService;

public class DeleteFromBucketController extends HttpServlet {
    private static final Long USER_ID = 1L;

    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        Long itemId = Long.valueOf(req.getParameter("item"));
        Bucket bucket = bucketService.get(USER_ID);
        Item item = itemService.get(itemId);
        bucketService.deleteItem(bucket, item);
        resp.sendRedirect(req.getContextPath() + "/index");
    }
}
