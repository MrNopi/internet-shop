package mate.academy.internetshop.services;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.exception.AuthorisationException;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.User;

public interface UserService {

    public User create(User user) throws DataProcessingException;

    public User get(Long userId) throws DataProcessingException;

    public User update(User user) throws DataProcessingException;

    public boolean delete(Long userId) throws DataProcessingException;

    public List<User> getAllUsers();

    public User login(String login, String password)
            throws AuthorisationException, DataProcessingException;

    Optional<User> findByToken(String token) throws DataProcessingException;
}
