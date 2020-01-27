package mate.academy.internetshop.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Role;
import mate.academy.internetshop.models.User;

@Dao
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String USER_TABLE = "users";
    private static final String USER_ROLE_TABLE = "users_roles";
    private static final String ROLE_TABLE = "roles";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public User create(User user) throws DataProcessingException {
        String query = String.format("INSERT INTO %s (login, password, token) VALUES (?, ?, ?)",
                USER_TABLE);
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
            throw new DataProcessingException("Unable to create user", e);
        }

        return addUserRole(user);
    }

    @Override
    public Optional<User> get(Long userId) throws DataProcessingException {
        User user;
        String query = String.format("SELECT %1$s.user_id, login, password, token, role FROM %1$s"
                + " INNER JOIN %2$s ON %1$s.user_id = %2$s.user_id"
                + " INNER JOIN roles ON %2$s.role_id = roles.role_id"
                + " WHERE %1$s.user_id = ?;", USER_TABLE, USER_ROLE_TABLE);
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
            throw new DataProcessingException("Unable to get user", e);
        }
        return Optional.of(user);
    }

    @Override
    public User update(User user) throws DataProcessingException {
        String query = String.format("UPDATE %s SET login=?, password=?, token=? WHERE user_id=?",
                USER_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getToken());
            stmt.setLong(4, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to update user", e);
        }
        return user;
    }

    @Override
    public boolean delete(Long userId) throws DataProcessingException {
        String query = String.format("DELETE FROM %s WHERE user_id=?", USER_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to delete user", e);
        }
    }

    @Override
    public Optional<User> getUserByLogin(String login) throws DataProcessingException {
        String query = String.format("SELECT user_id, password, token FROM %s WHERE login=?",
                USER_TABLE);
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
            throw new DataProcessingException("Unable to find user", e);
        }
        return Optional.of(user);
    }

    @Override
    public Optional<User> findByToken(String token) throws DataProcessingException {
        User user = null;
        String query = String.format("SELECT %1$s.user_id, login, password, token, role FROM %1$s"
                + " INNER JOIN %2$s ON %1$s.user_id = %2$s.user_id"
                + " INNER JOIN roles ON %2$s.role_id = roles.role_id"
                + " WHERE %1$s.token = ?;", USER_TABLE, USER_ROLE_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, token);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            setUser(rs);
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to find user", e);
        }
        return Optional.of(user);
    }

    @Override
    public List<User> getAllUsers() throws DataProcessingException {
        List<User> users = new ArrayList<>();
        String query = String.format("SELECT %1$s.user_id, login, password, role FROM shop.%1$s\n"
                + "INNER JOIN shop.%2$s ON %1$s.user_id = %2$s.user_id\n"
                + "INNER JOIN shop.%3$s ON %2$s.role_id = %3$s.role_id;", USER_TABLE, USER_ROLE_TABLE, ROLE_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                users.add(setUser(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to find user", e);
        }
    }

    private User addUserRole(User user) throws DataProcessingException {
        String query = String.format("INSERT INTO %s (user_id, role_id) VALUES (?, ?)", USER_ROLE_TABLE);
        try (PreparedStatement stmt = connection.prepareStatement(query,
                Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, user.getId());
            stmt.setInt(2, 1);
            stmt.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Unable to create user", e);
        }
    }
    private User setUser(ResultSet rs) throws SQLException {
        User user = new User(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setToken(rs.getString("token"));
        user.addRole(new Role(rs.getString("role")));
        user.setId(rs.getLong(1));
        return user;
    }
}
