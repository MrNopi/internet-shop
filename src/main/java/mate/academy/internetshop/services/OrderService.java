package mate.academy.internetshop.services;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;

public interface OrderService {

    public Order create(Order order);

    public Optional<Order> get(Long orderId);

    public Order update(Order order);

    public boolean delete(Long orderId);

    public Order completeOrder(List<Item> items, User user);

    public List<Order> getUserOrders(Long userId);
}
