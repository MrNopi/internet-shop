package mate.academy.internetshop.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.services.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    private static ItemDao itemDao;

    @Override
    public Item create(Item item) throws DataProcessingException {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long itemId) throws DataProcessingException {
        return itemDao.get(itemId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Item update(Item item) throws DataProcessingException {
        return itemDao.update(item);
    }

    @Override
    public boolean delete(Long itemId) throws DataProcessingException {
        return itemDao.delete(itemId);
    }

    @Override
    public List<Item> getAllItems() throws DataProcessingException {
        return itemDao.getAllItems();
    }
}
