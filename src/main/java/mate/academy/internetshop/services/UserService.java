package mate.academy.internetshop.services;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.exception.AuthorisationException;
import mate.academy.internetshop.models.User;

import javax.security.sasl.AuthenticationException;

public interface UserService {

    public User create(User user);

    public User get(Long userId);

    public User update(User user);

    public boolean delete(Long userId);

    public List<User> getAllUsers();

    public User login(String login, String password)
            throws AuthenticationException, AuthorisationException;

    Optional<User> findByToken(String token);
}
