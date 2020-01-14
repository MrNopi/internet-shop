package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.ItemService;
import org.apache.log4j.Logger;

public class AddItemInBucketController extends HttpServlet {
    private static final Long USER_ID = 1L;
    @Inject
    private static BucketService bucketService;
    @Inject
    private static ItemService itemService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long itemId = Long.valueOf(req.getParameter("itemId"));
        Bucket bucket = bucketService.get(USER_ID);
        bucketService.addItem(bucket, itemService.get(itemId));
        resp.sendRedirect(req.getContextPath() + "/ShowBucketItems");
    }
}
