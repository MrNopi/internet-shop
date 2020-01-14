package mate.academy.internetshop.dao.implementation;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoImpl implements ItemDao {
    private static Long id = 0L;
    private static final Logger LOGGER = Logger.getLogger(ItemDaoImpl.class);

    @Override
    public Item create(Item item) {
        Storage.items.add(item.setId(++id));
        return item;
    }

    @Override
    public Optional<Item> get(Long itemId) {
        return Storage.items.stream()
                .filter(x -> x.getId().equals(itemId))
                .findFirst();
    }

    @Override
    public Item update(Item item) {
        try {
            Item temp = Storage.items.stream()
                    .filter(x -> x.getId().equals(item.getId()))
                    .findFirst()
                    .get();
            int index = Storage.items.indexOf(temp);
            Storage.items.set(index, item);
            return Storage.items.get(index);
        } catch (NoSuchElementException e) {
            LOGGER.error("There is no such an element in storage");
        }
        return null;
    }

    @Override
    public boolean delete(Long itemId) {
        boolean isRemoved = false;
        try {
            Item tempItem = Storage.items.stream()
                    .filter(x -> x.getId().equals(itemId))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);
            isRemoved = Storage.items.remove(tempItem);
        } catch (NoSuchElementException e) {
            LOGGER.error("No such element in Storage");
        }
        return isRemoved;
    }
}
