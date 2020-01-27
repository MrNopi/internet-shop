package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;

public interface OrderDao {

    Order create(Order order) throws DataProcessingException;

    Optional<Order> get(Long orderId) throws DataProcessingException;

    Order update(Order order) throws DataProcessingException;

    boolean delete(Long orderId) throws DataProcessingException;

    Order completeOrder(List<Item> items, User user) throws DataProcessingException;
}
