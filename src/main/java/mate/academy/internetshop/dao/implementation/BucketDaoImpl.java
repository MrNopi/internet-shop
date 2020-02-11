package mate.academy.internetshop.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;

@Dao
public class BucketDaoImpl extends AbstractDao<Bucket> implements BucketDao {
    private static final String BUCKETS_TABLE = "buckets";
    private static final String ITEMS_TABLE = "items";
    private static final String BUCKET_ITEMS_TABLE = "bucket_items";

    public BucketDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) throws DataProcessingException {
        String query = String.format("INSERT INTO %s(user_id) VALUES(?)", BUCKETS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, bucket.getUserId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            bucket.setId(rs.getLong(1));
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create new Bucket", e);
        }
        return bucket;
    }

    @Override
    public Optional<Bucket> get(Long bucketId) throws DataProcessingException {
        String query = String.format(
                "SELECT %1$s.bucket_id, %1$s.user_id, %2$s.name, %2$s.price FROM %2$s\n"
                        + "INNER JOIN %3$s ON %3$s.item_id = %2$s.id\n"
                        + "INNER JOIN %1$s ON %1$s.bucket_id = %3$s.bucket_id\n"
                        + "WHERE %3$s.bucket_id = ?;", BUCKETS_TABLE,
                ITEMS_TABLE, BUCKET_ITEMS_TABLE);
        Bucket bucket = null;
        List<Item> items = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, bucketId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item item = new Item(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setId(rs.getLong(1));
                items.add(item);
            }
            bucket = new Bucket(rs.getLong("user_id"));
            bucket.setId(bucketId);
            bucket.setItems(items);
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to get Bucket", e);
        }
        return Optional.ofNullable(bucket);
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        String query = String.format("UPDATE %s SET(item_id=?)", BUCKET_ITEMS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            for (Item item : bucket.getItems()) {
                stmt.setLong(1, item.getId());
                stmt.executeUpdate();
            }
            bucket = get(bucket.getId()).orElseThrow(NoSuchElementException::new);
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update bucket ", e);
        }
        return bucket;
    }

    @Override
    public boolean delete(Long bucketId) throws DataProcessingException {
        clear(bucketId);
        String query = String.format("DELETE FROM %s WHERE bucket_id=?;", BUCKETS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, bucketId);
            stmt.setLong(1, bucketId);
            return stmt.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot perform remove for current bucket, cuz of: ",
                    e);
        }
    }

    public boolean clear(Long bucketId) throws DataProcessingException {
        String query = String.format("DELETE FROM %s WHERE bucket_id=?;\n", BUCKET_ITEMS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, bucketId);
            stmt.setLong(1, bucketId);
            return stmt.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't clean current bucket, cuz of: ", e);
        }
    }

    public boolean addItemInBucket(Item item, Long bucketId)
            throws DataProcessingException {
        String query = String.format("INSERT INTO %s(bucket_id, item_id) VALUES(?,?)",
                BUCKET_ITEMS_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, bucketId);
            stmt.setLong(2, item.getId());
            return stmt.execute();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't clean current bucket, cuz of: ", e);
        }
    }
}
