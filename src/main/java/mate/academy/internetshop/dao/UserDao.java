package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.User;

public interface UserDao {

    User create(User user) throws DataProcessingException;

    Optional<User> get(Long userId) throws DataProcessingException;

    User update(User user) throws DataProcessingException;

    boolean delete(Long userId) throws DataProcessingException;

    Optional<User> getUserByLogin(String login) throws DataProcessingException;

    Optional<User> findByToken(String token) throws DataProcessingException;

    List<User> getAllUsers() throws DataProcessingException;
}
