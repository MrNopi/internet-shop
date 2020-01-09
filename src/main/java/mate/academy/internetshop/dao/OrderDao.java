package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.models.Order;

public interface OrderDao {

    Order create(Order order);

    Optional<Order> get(Long orderId);

    Order update(Order order);

    boolean delete(Long orderId);
}
