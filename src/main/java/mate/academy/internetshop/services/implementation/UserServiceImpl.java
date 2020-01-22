package mate.academy.internetshop.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.exception.AuthorisationException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long userId) {
        User user = userDao.get(userId)
                .orElseThrow(NoSuchElementException::new);
        return user;
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(Long userId) {
        return userDao.delete(userId);
    }

    public List<User> getAllUsers() {
        return Storage.users;
    }

    @Override
    public User login(String login, String password)
            throws AuthorisationException {
        Optional<User> user = userDao.getUserByLogin(login);
        if (user.isEmpty() || !user.get().getPassword().equals(password)) {
            throw new AuthorisationException("Wrong username or password");
        }
        return user.get();
    }

    @Override
    public Optional<User> findByToken(String token) {
        return userDao.findByToken(token);
    }
}
