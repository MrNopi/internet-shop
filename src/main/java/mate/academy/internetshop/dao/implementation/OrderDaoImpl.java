package mate.academy.internetshop.dao.implementation;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Order;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoImpl implements OrderDao {
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);
    private static Long id = 0L;

    @Override
    public Order create(Order order) {
        order.setId(++id);
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long orderId) {
        return Storage.orders.stream()
                .filter(x -> x.getId().equals(orderId))
                .findFirst();
    }

    @Override
    public Order update(Order order) {
        LOGGER.error("Error occured when tried to update order");
            Order temp = Storage.orders.stream()
                    .filter(x -> x.getId().equals(order.getId()))
                    .findFirst().orElseThrow(NoSuchElementException::new);
        int index = Storage.orders.indexOf(temp);
        return Storage.orders.set(index, order);
    }

    @Override
    public boolean delete(Long orderId) {
        LOGGER.error("Error occured when tried to delete order");
            Order toRemove = Storage.orders.stream()
                    .filter(x -> x.getId().equals(orderId))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);
            return Storage.orders.remove(toRemove);
    }
}
