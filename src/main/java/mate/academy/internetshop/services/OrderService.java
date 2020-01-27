package mate.academy.internetshop.services;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;

public interface OrderService {

    public Order create(Order order) throws DataProcessingException;

    public Optional<Order> get(Long orderId) throws DataProcessingException;

    public Order update(Order order) throws DataProcessingException;

    public boolean delete(Long orderId) throws DataProcessingException;

    public Order completeOrder(List<Item> items, User user) throws DataProcessingException;

    public List<Order> getUserOrders(Long userId);
}
