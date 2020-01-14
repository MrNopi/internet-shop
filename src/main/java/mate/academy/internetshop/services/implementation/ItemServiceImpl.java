package mate.academy.internetshop.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.services.ItemService;
import org.apache.log4j.Logger;

@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    private static ItemDao itemDao;
    private static final Logger LOGGER = Logger.getLogger(ItemServiceImpl.class);

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Item get(Long itemId) {
        LOGGER.error("Error occured when tried to update bucket");
            return itemDao.get(itemId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public boolean delete(Long itemId) {
        return itemDao.delete(itemId);
    }

    @Override
    public List<Item> getAllItems() {
        return Storage.items;
    }
}
