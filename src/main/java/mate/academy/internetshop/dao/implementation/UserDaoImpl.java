package mate.academy.internetshop.dao.implementation;

import java.util.NoSuchElementException;
import java.util.Optional;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.User;

@Dao
public class UserDaoImpl implements UserDao {
    private static Long id = 0L;

    @Override
    public User create(User user) {
        Storage.users.add(user.setId(++id));
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
        User toUpdate = Storage.users.stream()
                .filter(x -> x.getId().equals(user.getId()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        int index = Storage.users.indexOf(toUpdate);
        return Storage.users.set(index, user);
    }

    @Override
    public boolean delete(long userId) {
        User toRemove = Storage.users.stream()
                .filter(x -> x.getId().equals(userId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
        Storage.users.remove(toRemove);
        return true;
    }
}
