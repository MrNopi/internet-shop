package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.models.Order;

public interface ItemDao extends GenericDao<Item> {

    List<Item> getAllItems() throws DataProcessingException;
}
