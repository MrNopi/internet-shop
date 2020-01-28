package mate.academy.internetshop.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Item;

@Dao
public class ItemDaoImpl extends AbstractDao<Item> implements ItemDao {
    private static final String ITEMS_TABLE = "items";

    public ItemDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) throws DataProcessingException {
        String query = String.format("INSERT INTO %s(name, price) VALUES(?, ?)",
                ITEMS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.executeUpdate(query);
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            item.setId(rs.getLong(1));
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot perform create for the current item with id ",
                    e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long itemId) throws DataProcessingException {
        String query = String.format("SELECT id, name, price FROM %s WHERE id=?",
                ITEMS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, itemId);
            ResultSet rs = stmt.executeQuery(query);
            String itemName = rs.getString("name");
            Double itemPrice = rs.getDouble("price");
            return Optional.of(new Item(itemName).setPrice(itemPrice).setId(rs.getLong(1)));
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create item", e);
        }
    }

    @Override
    public Item update(Item item) throws DataProcessingException {
        String query = String.format("UPDATE shop.%s SET name=?, price=? WHERE id=?",
                ITEMS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, item.getName());
            stmt.setDouble(1, item.getPrice());
            stmt.setLong(1, item.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update item", e);
        }
        return item;
    }

    @Override
    public boolean delete(Long itemId) throws DataProcessingException {
        String query = String.format("DELETE FROM shop.%s WHERE id=?",
                ITEMS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, itemId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete item", e);
        }
    }

    @Override
    public List<Item> getAllItems() throws DataProcessingException {
        List<Item> items = new ArrayList<>();
        String query = String.format("SELECT * FROM %s;", ITEMS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                items.add(new Item(result.getString("name"))
                        .setPrice(result.getDouble("price"))
                        .setId(result.getLong(1)));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get item", e);
        }
        return items;
    }
}
