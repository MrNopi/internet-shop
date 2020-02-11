package mate.academy.internetshop.dao;

import java.util.List;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;

public interface OrderDao extends GenericDao<Order> {

    Order completeOrder(List<Item> items, User user) throws DataProcessingException;

    List<Order> getAllOrders(Long userId) throws DataProcessingException;
}
