package mate.academy.internetshop.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.UserService;
import org.apache.log4j.Logger;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private static UserDao userDao;
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User get(Long userId) {
        User user = null;
        try {
            user = userDao.get(userId)
                    .get();
        } catch (NoSuchElementException e) {
            LOGGER.error("No such element in Storage");
        }
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
}
