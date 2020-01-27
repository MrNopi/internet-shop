package mate.academy.internetshop.dao;

import java.util.Optional;
import mate.academy.internetshop.models.User;

public interface UserDao {

    User create(User user);

    Optional<User> get(Long userId);

    User update(User user);

    boolean delete(Long userId);

    Optional<User> getUserByLogin(String login);

    Optional<User> findByToken(String token);
}
