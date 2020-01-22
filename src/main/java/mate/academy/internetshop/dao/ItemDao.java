package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.models.Item;

public interface ItemDao {

    Item create(Item item);

    Optional<Item> get(Long itemId);

    Item update(Item item);

    boolean delete(Long itemId);

    List<Item> getAllItems();
}
