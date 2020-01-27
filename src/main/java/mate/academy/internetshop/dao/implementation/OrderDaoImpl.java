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
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;
import org.apache.log4j.Logger;

@Dao
public class OrderDaoImpl extends AbstractDao<Order> implements OrderDao {

    private static final String ORDERS_TABLE = "orders";
    private static final String ORDERS_ITEMS_TABLE = "orders_items";
    private static final String ITEMS_TABLE = "items";
    private static final Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) {
        String query = String.format("INSERT INTO %s(orders_user_id) VALUES(?)", ORDERS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, order.getUserId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            order.setId(rs.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public Optional<Order> get(Long orderId) {
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
            LOGGER.error("Cannot get an appropriate order by id " + orderId, e);
        }
        return Optional.of(order);
    }

    @Override
    public Order update(Order order) {
        String query = String.format("DELETE FROM %s FROM WHERE order_id=?; \n"
                + "DELETE FROM %s WHERE order_id=?", ORDERS_ITEMS_TABLE, ORDERS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, order.getId());
            stmt.setLong(2, order.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot perform update for the current order with id "
                    + order.getId() + "\n", e);
        }
        query = String.format("INSERT INTO %s (orders_user_id) VALUES(?)", ORDERS_TABLE);
        Long orderId = null;
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, order.getUserId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            orderId = rs.getLong(1);
        } catch (SQLException e) {
            LOGGER.error("Cannot perform update for the current order with id "
                    + order.getId() + "\n", e);
        }
        if (orderId != null) {
            query = String.format("INSERT INTO %s (order_id, item_id) VALUES (?,?)",
                    ORDERS_ITEMS_TABLE);
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setLong(1, orderId);
                for (Item item : order.getItems()) {
                    stmt.setLong(2, item.getId());
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return order;
    }

    @Override
    public boolean delete(Long orderId) {
        String query = String.format("DELETE FROM %s WHERE order_id=?\n"
                + "DELETE FROM %s WHERE order_id=?", ORDERS_ITEMS_TABLE, ORDERS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, orderId);
            stmt.setLong(2, orderId);
            return stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
