package mate.academy.internetshop.services;

import java.util.List;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Item;

public interface ItemService {

    public Item create(Item item) throws DataProcessingException;

    public Item get(Long itemId) throws DataProcessingException;

    public Item update(Item item) throws DataProcessingException;

    public boolean delete(Long itemId) throws DataProcessingException;

    public List<Item> getAllItems() throws DataProcessingException;
}
