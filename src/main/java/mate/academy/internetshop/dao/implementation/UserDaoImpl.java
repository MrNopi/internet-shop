package mate.academy.internetshop.dao.implementation;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.User;
import org.apache.log4j.Logger;

@Dao
public class UserDaoImpl implements UserDao {
    private static Long id = 0L;
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Override
    public User create(User user) {
        user.setId(++id);
        Storage.users.add(user);
        return user;
    }

    @Override
    public Optional<User> get(long userId) {
        return Storage.users.stream()
                .filter(x -> x.getId().equals(userId))
                .findFirst();
    }

    @Override
    public User update(User user) {
        User toUpdate = null;
        try {
            toUpdate = Storage.users.stream()
                    .filter(x -> x.getId().equals(user.getId()))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e) {
            LOGGER.error("No such element in Storage");
        }
        int index = Storage.users.indexOf(toUpdate);
        return Storage.users.set(index, user);
    }

    @Override
    public boolean delete(long userId) {
        boolean isRemoved = false;
        try {
            User toRemove = Storage.users.stream()
                    .filter(x -> x.getId().equals(userId))
                    .findFirst()
                    .get();
            isRemoved = Storage.users.remove(toRemove);
        } catch (NoSuchElementException e) {
            LOGGER.error("No such element in Storage");
        }
        return isRemoved;
    }
}
