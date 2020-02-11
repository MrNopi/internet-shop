package mate.academy.internetshop.dao;

import java.util.List;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Item;

public interface ItemDao extends GenericDao<Item> {

    List<Item> getAllItems() throws DataProcessingException;
}
