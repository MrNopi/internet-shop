package mate.academy.internetshop.services;

import java.util.List;
import mate.academy.internetshop.models.User;

public interface UserService {

    public User create(User user);

    public User get(Long userId);

    public User update(User user);

    public boolean delete(Long userId);

    public List<User> getAllUsers();
}
