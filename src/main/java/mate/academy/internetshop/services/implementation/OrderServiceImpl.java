package mate.academy.internetshop.services.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Inject
    private static OrderDao orderDao;

    @Override
    public Order create(Order order) throws DataProcessingException {
        return orderDao.create(order);
    }

    @Override
    public Optional<Order> get(Long orderId) throws DataProcessingException {
        return orderDao.get(orderId);
    }

    @Override
    public Order update(Order order) throws DataProcessingException {
        return orderDao.update(order);
    }

    @Override
    public boolean delete(Long orderId) throws DataProcessingException {
        return orderDao.delete(orderId);
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Order order = new Order(user.getId()).setItems(items);
        Storage.orders.add(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return Storage.orders.stream()
                .filter(x -> x.getUserId().equals(userId))
                .collect(Collectors.toList());
    }
}
