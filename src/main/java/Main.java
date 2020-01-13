import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.BucketService;
import mate.academy.internetshop.services.ItemService;
import mate.academy.internetshop.services.OrderService;
import mate.academy.internetshop.services.UserService;
import mate.academy.internetshop.services.implementation.BucketServiceImpl;
import mate.academy.internetshop.services.implementation.ItemServiceImpl;
import mate.academy.internetshop.services.implementation.OrderServiceImpl;
import mate.academy.internetshop.services.implementation.UserServiceImpl;

import java.util.List;

public class Main {

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BucketService bucketService = new BucketServiceImpl();
        ItemService itemService = new ItemServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        UserService userService = new UserServiceImpl();

        User vasya = userService.create(new User("Vasya"));
        User jeremy = userService.create(new User("Jeremy"));
        Bucket bucket = new Bucket(vasya);
        bucketService.create(bucket);
        System.out.println(bucketService.get(bucket.getId()));
        bucket.setUserId(jeremy.getId());
        System.out.println("Vasya: " + vasya.getId());
        System.out.println("Jeremy: " + jeremy.getId());
        System.out.println(bucketService.update(bucket));

        //ItemService
        itemService.create(new Item("T-Shirt"));
        itemService.create(new Item("Hoodie"));
        itemService.create(new Item("Longsleeve"));
        List<Item> items = itemService.getAllItems();
        items.forEach(x -> System.out.println(itemService.get(x.getId())));
        itemService.delete(items.get(2).getId());
        List<Item> updatedItems = itemService.getAllItems();

        //OrderService
        orderService.create(new Order(vasya));
        orderService.create(new Order(vasya));
        orderService.create(new Order(jeremy));

        System.out.println(orderService.get(2));
        orderService.delete(2);
        orderService.update(orderService.get(1).get());
        orderService.getUserOrders(vasya).forEach(System.out::println);
        System.out.println(orderService.completeOrder(items, jeremy));

    }

}
