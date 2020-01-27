package mate.academy.internetshop.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Role;
import mate.academy.internetshop.models.User;
import org.apache.log4j.Logger;

@Dao
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);
    private static final String USER_TABLE_NAME = "users";
    private static final String ROLE_TABLE_NAME = "users_roles";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) {
        String query = String.format("INSERT INTO %s (login, password, token) VALUES (?, ?, ?)",
                USER_TABLE_NAME);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getToken());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            user.setId(rs.getLong(1));
        } catch (SQLException e) {
            LOGGER.error("Can't create user \n error: ", e);
        }
        query = String.format("INSERT INTO %s (user_id, role_id) VALUES (?, ?)", ROLE_TABLE_NAME);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, user.getId());
            stmt.setInt(2, 1);
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can't create user \n error: ", e);
        }
        return user;
    }

    @Override
    public Optional<User> get(Long userId) {
        User user = null;
        String query = String.format("SELECT %1$s.user_id, login, password, token, role FROM %1$s"
                + " INNER JOIN %2$s ON %1$s.user_id = %2$s.user_id"
                + " INNER JOIN roles ON %2$s.role_id = roles.role_id"
                + " WHERE %1$s.user_id = ?;", USER_TABLE_NAME, ROLE_TABLE_NAME);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, userId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            user = new User(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setToken(rs.getString("token"));
            user.addRole(new Role(rs.getString("role")));
            user.setId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User update(User user) {
        String query = String.format("UPDATE %s SET login=?, password=?, token=? WHERE user_id=?",
                USER_TABLE_NAME);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getToken());
            stmt.setLong(4, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean delete(Long userId) {
        String query = String.format("DELETE FROM %s WHERE user_id=?", USER_TABLE_NAME);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        String query = String.format("SELECT user_id, password, token FROM %s WHERE login=?",
                USER_TABLE_NAME);
        User user = new User(login);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            user.setPassword(rs.getString("password"));
            user.setToken(rs.getString("token"));
            user.setId(rs.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByToken(String token) {
        User user = null;
        String query = String.format("SELECT %1$s.user_id, login, password, token, role FROM %1$s"
                + " INNER JOIN %2$s ON %1$s.user_id = %2$s.user_id"
                + " INNER JOIN roles ON %2$s.role_id = roles.role_id"
                + " WHERE %1$s.token = ?;", USER_TABLE_NAME, ROLE_TABLE_NAME);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            user = new User(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setToken(rs.getString("token"));
            user.addRole(new Role(rs.getString("role")));
            user.setId(rs.getLong(1));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }
}
