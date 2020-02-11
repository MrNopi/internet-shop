package mate.academy.internetshop.services;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.exception.AuthorisationException;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.User;

public interface UserService {

    User create(User user) throws DataProcessingException;

    User get(Long userId) throws DataProcessingException;

    User update(User user) throws DataProcessingException;

    boolean delete(Long userId) throws DataProcessingException;

    List<User> getAllUsers() throws DataProcessingException;

    User login(String login, String password)
            throws AuthorisationException, DataProcessingException;

    Optional<User> findByToken(String token) throws DataProcessingException;
}
