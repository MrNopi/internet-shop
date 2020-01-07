package mate.academy.internetshop.services;

import mate.academy.internetshop.models.User;

public interface UserService {

    public User create(User user);

    public User get(long userId);

    public User update(User user);

    public boolean delete(long userId);
}