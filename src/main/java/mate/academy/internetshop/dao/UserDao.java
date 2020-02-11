package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.User;

public interface UserDao extends GenericDao<User>{

    Optional<User> getUserByLogin(String login) throws DataProcessingException;

    Optional<User> findByToken(String token) throws DataProcessingException;

    List<User> getAllUsers() throws DataProcessingException;
}
