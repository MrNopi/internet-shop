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
        Order temp = null;
        try {
            temp = Storage.orders.stream()
                    .filter(x -> x.getId().equals(order.getId()))
                    .findFirst().orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            LOGGER.error("No such element in Storage");
        }
        int index = Storage.orders.indexOf(temp);
        return Storage.orders.set(index, order);
    }

    @Override
    public boolean delete(Long orderId) {
        boolean isRemoved = false;
        try {
            Order toRemove = Storage.orders.stream()
                    .filter(x -> x.getId().equals(orderId))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);
            isRemoved = Storage.orders.remove(toRemove);
        } catch (NoSuchElementException e) {
            LOGGER.error("No such element in Storage");
        }
        return isRemoved;
    }
}
