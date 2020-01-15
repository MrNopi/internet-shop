package mate.academy.internetshop.services;

import java.util.List;
import mate.academy.internetshop.models.Item;

public interface ItemService {

    public Item create(Item item);

    public Item get(Long itemId);

    public Item update(Item item);

    public boolean delete(Long itemId);

    public List<Item> getAllItems();
}
