package mate.academy.internetshop.lib;

import mate.academy.internetshop.dao.implementation.BucketImpl;
import mate.academy.internetshop.dao.implementation.ItemImpl;
import mate.academy.internetshop.dao.implementation.OrderImpl;
import mate.academy.internetshop.dao.implementation.UserImpl;
import mate.academy.internetshop.services.implementation.BucketServiceImpl;
import mate.academy.internetshop.services.implementation.ItemServiceImpl;
import mate.academy.internetshop.services.implementation.OrderServiceImpl;
import mate.academy.internetshop.services.implementation.UserServiceImpl;

public class Factory {
    private static UserImpl userImpl;
    private static BucketImpl bucketImpl;
    private static ItemImpl itemImpl;
    private static OrderImpl orderImpl;
    private static UserServiceImpl userService;
    private static BucketServiceImpl bucketService;
    private static ItemServiceImpl itemService;
    private static OrderServiceImpl orderService;

    public static UserImpl getUserImpl() {
        if (userImpl == null) {
            userImpl = new UserImpl();
        }
        return userImpl;
    }

    public static BucketImpl getBucketImpl() {
        if (bucketImpl == null) {
            bucketImpl = new BucketImpl();
        }
        return bucketImpl;
    }

    public static ItemImpl getItemImpl() {
        if (itemImpl == null) {
            itemImpl = new ItemImpl();
        }
        return itemImpl;
    }

    public static OrderImpl getOrderImpl() {
        if (orderImpl == null) {
            orderImpl = new OrderImpl();
        }
        return orderImpl;
    }

    public static UserServiceImpl getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl();
        }
        return userService;
    }

    public static BucketServiceImpl getBucketService() {
        if (bucketService == null) {
            bucketService = new BucketServiceImpl();
        }
        return bucketService;
    }

    public static ItemServiceImpl getItemService() {
        if (itemService == null) {
            itemService = new ItemServiceImpl();
        }
        return itemService;
    }

    public static OrderServiceImpl getOrderService() {
        if (orderService == null) {
            orderService = new OrderServiceImpl();
        }
        return orderService;
    }
}
