package mate.academy.internetshop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.UserService;

public class ShowBucketItemsController extends HttpServlet {
    private static final Long USER_ID = 1L;
    @Inject
    private static UserService userService;
    @Inject
    private static BucketService bucketService;

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        User user = userService.get(USER_ID);
        Bucket bucket = bucketService.get(USER_ID);
        req.setAttribute("user", user);
        req.setAttribute("bucket", bucket.getAllItems());
        req.getRequestDispatcher("WEB-INF/views/Bucket.jsp").forward(req, resp);
    }
}
