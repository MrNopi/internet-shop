package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Item;

public interface ItemDao {

    Item create(Item item) throws DataProcessingException;

    Optional<Item> get(Long itemId) throws DataProcessingException;

    Item update(Item item) throws DataProcessingException;

    boolean delete(Long itemId) throws DataProcessingException;

    List<Item> getAllItems() throws DataProcessingException;
}
