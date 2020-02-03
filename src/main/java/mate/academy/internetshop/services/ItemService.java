package mate.academy.internetshop.services;

import java.util.List;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Item;

public interface ItemService {

    Item create(Item item) throws DataProcessingException;

    Item get(Long itemId) throws DataProcessingException;

    Item update(Item item) throws DataProcessingException;

    boolean delete(Long itemId) throws DataProcessingException;

    List<Item> getAllItems() throws DataProcessingException;
}
