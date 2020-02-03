package mate.academy.internetshop.services;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;

public interface OrderService {

    Order create(Order order) throws DataProcessingException;

    Optional<Order> get(Long orderId) throws DataProcessingException;

    Order update(Order order) throws DataProcessingException;

    boolean delete(Long orderId) throws DataProcessingException;

    Order completeOrder(List<Item> items, User user) throws DataProcessingException;

    List<Order> getUserOrders(Long userId) throws DataProcessingException;
}
