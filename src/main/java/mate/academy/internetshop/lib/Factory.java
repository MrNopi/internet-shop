package mate.academy.internetshop.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import mate.academy.internetshop.dao.implementation.BucketDaoImpl;
import mate.academy.internetshop.dao.implementation.ItemDaoImpl;
import mate.academy.internetshop.dao.implementation.OrderDaoImpl;
import mate.academy.internetshop.dao.implementation.UserDaoImpl;
import mate.academy.internetshop.services.implementation.BucketServiceImpl;
import mate.academy.internetshop.services.implementation.ItemServiceImpl;
import mate.academy.internetshop.services.implementation.OrderServiceImpl;
import mate.academy.internetshop.services.implementation.UserServiceImpl;
import org.apache.log4j.Logger;

public class Factory {
    private static UserDaoImpl userDaoImpl;
    private static BucketDaoImpl bucketDaoImpl;
    private static ItemDaoImpl itemDaoImpl;
    private static OrderDaoImpl orderDaoImpl;
    private static UserServiceImpl userService;
    private static BucketServiceImpl bucketService;
    private static ItemServiceImpl itemService;
    private static OrderServiceImpl orderService;
    private static Connection connection;
    private static final Logger LOGGER = Logger.getLogger(Factory.class);
    private static final String DB_NAME = "items";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?useUnicode=true&useJDBCCompliantTimezoneShift=true&serverTimezone=UTC", "root", "Smartlike1998");
            } catch (ClassNotFoundException | SQLException e) {
                LOGGER.error("Unable to connect to database, named " + DB_NAME, e);
            }
        }
        return connection;
    }

    public static UserDaoImpl getUserDaoImpl() {
        if (userDaoImpl == null) {
            userDaoImpl = new UserDaoImpl();
        }
        return userDaoImpl;
    }

    public static BucketDaoImpl getBucketDaoImpl() {
        if (bucketDaoImpl == null) {
            bucketDaoImpl = new BucketDaoImpl();
        }
        return bucketDaoImpl;
    }

    public static ItemDaoImpl getItemDaoImpl() {
        if (itemDaoImpl == null) {
            itemDaoImpl = new ItemDaoImpl(getConnection());
        }
        return itemDaoImpl;
    }

    public static OrderDaoImpl getOrderDaoImpl() {
        if (orderDaoImpl == null) {
            orderDaoImpl = new OrderDaoImpl();
        }
        return orderDaoImpl;
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
