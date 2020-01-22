package mate.academy.internetshop.dao.implementation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoImpl implements ItemDao {
    private static Long id = 0L;
    @Inject
    private static Connection connection;
    private static final String DB_NAME = "items";
    private static final Logger LOGGER = Logger.getLogger(ItemDaoImpl.class);

    @Override
    public Item create(Item item) {
        try(Statement stmt = connection.createStatement()) {
            String query = String.format("INSERT INTO shop.%s(name, price) VALUES('%s', %s)",
                    DB_NAME, item.getName(), item.getPrice());
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            LOGGER.error("Unable to execute query at CREATE at class " + this.getClass(), e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long itemId) {
        try(Statement stmt = connection.createStatement()) {
            String query = String.format("SELECT id, name, price FROM shop.%s WHERE id=%d",
                    DB_NAME, itemId);
            ResultSet rs = stmt.executeQuery(query);
                String itemName = rs.getString("name");
                Double itemPrice = rs.getDouble("price");
                return Optional.of(new Item(itemName).setPrice(itemPrice));
        } catch (SQLException e) {
            LOGGER.error("Unable to execute query at GET at class " + this.getClass(), e);
        }
        return Optional.empty();
    }

    @Override
    public Item update(Item item) {
        try(Statement stmt = connection.createStatement()) {
            String query = String.format("UPDATE shop.%s SET name=%s, price=%f WHERE id=%d",
                    DB_NAME, item.getName(), item.getPrice(), item.getId());
            stmt.executeUpdate(query);
                return item;
        } catch (SQLException e) {
            LOGGER.error("Unable to execute query at GET at class " + this.getClass(), e);
        }
        return null;
    }

    @Override
    public boolean delete(Long itemId) {
        try(Statement stmt = connection.createStatement()) {
            String query = String.format("DELETE FROM shop.%s WHERE id=%d",
                    DB_NAME, itemId);
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            LOGGER.error("Unable to execute query at GET at class " + this.getClass(), e);
        }
        return false;
    }
}
