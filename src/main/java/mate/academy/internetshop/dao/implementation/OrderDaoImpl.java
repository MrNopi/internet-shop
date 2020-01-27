package mate.academy.internetshop.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;
import mate.academy.internetshop.models.User;

@Dao
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {
    private static final String ORDERS_TABLE = "orders";
    private static final String ORDERS_ITEMS_TABLE = "orders_items";
    private static final String ITEMS_TABLE = "items";

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) throws DataProcessingException {
        String query = String.format("INSERT INTO %s(orders_user_id) VALUES(?)", ORDERS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, order.getUserId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            order.setId(rs.getLong(1));
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create order", e);
        }
        return order;
    }

    @Override
    public Optional<Order> get(Long orderId) throws DataProcessingException {
        Order order = new Order();
        List<Item> items = new ArrayList<>();
        String query = String.format(
                "SELECT %2$s.order_id, %2$s.orders_user_id item_id, name, price FROM %1$s\n"
                        + "INNER JOIN %2$s ON %1$s.order_id=%2$s.order_id\n"
                        + "INNER JOIN %3$s ON item_id = id\n"
                        + "WHERE orders_user_id = ?;",
                ORDERS_ITEMS_TABLE, ORDERS_TABLE, ITEMS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, orderId);
            ResultSet rs = stmt.executeQuery();
            order.setId(orderId);
            order.setId(stmt.getGeneratedKeys().getLong(1));
            while (rs.next()) {
                Item item = new Item(rs.getString("name"));
                item.setId(rs.getLong(3));
                item.setPrice(rs.getDouble("price"));
                items.add(item);
            }
            order.setItems(items);
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot get an appropriate order by id " + orderId, e);
        }
        return Optional.of(order);
    }

    @Override
    public Order update(Order order) throws DataProcessingException {
        String query = String.format("DELETE FROM %s FROM WHERE order_id=?; \n"
                + "DELETE FROM %s WHERE order_id=?;", ORDERS_ITEMS_TABLE, ORDERS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, order.getId());
            stmt.setLong(2, order.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot perform update for the current order with id "
                    + order.getId() + "\n", e);
        }
        create(order);
        return addAllItems(order);
    }

    @Override
    public boolean delete(Long orderId) throws DataProcessingException {
        String query = String.format("DELETE FROM %s WHERE order_id=?\n"
                + "DELETE FROM %s WHERE order_id=?", ORDERS_ITEMS_TABLE, ORDERS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, orderId);
            stmt.setLong(2, orderId);
            return stmt.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete order", e);
        }
    }

    @Override
    public Order completeOrder(List<Item> items, User user) throws DataProcessingException {
        String query = String.format("INSERT INTO %s(user_id) VALUES(?)", ORDERS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            Order order = addAllItems(items, rs.getLong(1));
            order.setUserId(user.getId());
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete order", e);
        }
    }

    private Order addAllItems(List<Item> items, Long orderId) throws DataProcessingException {
        String query = String.format("INSERT INTO %s(order_id, item_id) VALUES(?,?)", ORDERS_ITEMS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            Order order = new Order();
            order.setId(orderId);
            stmt.setLong(1, orderId);
            for (Item item : items) {
                stmt.setLong(2, item.getId());
                order.addItem(item);
                stmt.executeUpdate();
            }
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete order", e);
        }
    }

    private Order addAllItems(Order order) throws DataProcessingException {
        String query = String.format("INSERT INTO %s (order_id, item_id) VALUES (?,?)",
                ORDERS_ITEMS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, order.getId());
            for (Item item : order.getItems()) {
                stmt.setLong(2, item.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update order", e);
        }
        return order;
    }
}
