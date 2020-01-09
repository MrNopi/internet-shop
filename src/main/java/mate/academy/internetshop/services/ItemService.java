package mate.academy.internetshop.services;

import java.util.List;
import mate.academy.internetshop.models.Item;

public interface ItemService {

    public Item create(Item item);

    public Item get(long itemId);

    public Item update(Item item);

    public boolean delete(long itemId);

    public List<Item> getAllItems();
}
