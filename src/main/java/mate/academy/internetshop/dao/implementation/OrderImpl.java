package mate.academy.internetshop.dao.implementation;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Order;

@Dao
public class OrderImpl implements OrderDao {

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
        Order temp = Storage.orders.stream()
                .filter(x -> x.getId().equals(order.getId()))
                .findFirst().orElseThrow(NoSuchElementException::new);
        int index = Storage.orders.indexOf(temp);
        return Storage.orders.set(index, order);
    }

    @Override
    public boolean delete(Long orderId) {
        Order toRemove = Storage.orders.stream()
                .filter(x -> x.getId().equals(orderId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        Storage.orders.remove(toRemove);
        return true;
    }
}
