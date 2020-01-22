package mate.academy.internetshop.lib;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.ItemService;
import mate.academy.internetshop.services.OrderService;
import mate.academy.internetshop.services.UserService;

public class AnnotatedClassMap {
    private static final Map<Class<?>, Object> classMap = new HashMap<>();

    static {
        classMap.put(UserDao.class, Factory.getUserDaoImpl());
        classMap.put(BucketDao.class, Factory.getBucketDaoImpl());
        classMap.put(ItemDao.class, Factory.getItemDaoImpl());
        classMap.put(OrderDao.class, Factory.getOrderDaoImpl());
        classMap.put(UserService.class, Factory.getUserService());
        classMap.put(BucketService.class, Factory.getBucketService());
        classMap.put(ItemService.class, Factory.getItemService());
        classMap.put(OrderService.class, Factory.getOrderService());
    }

    public static Object getImplementation(Class<?> interfaceClass) {
        return classMap.get(interfaceClass);
    }
}
