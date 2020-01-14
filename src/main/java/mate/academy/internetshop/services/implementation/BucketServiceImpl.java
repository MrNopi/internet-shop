package mate.academy.internetshop.services.implementation;

import java.util.List;
import java.util.NoSuchElementException;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.services.BucketService;
import org.apache.log4j.Logger;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    static BucketDao bucketDao;
    private static final Logger LOGGER = Logger.getLogger(BucketServiceImpl.class);

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(long bucketId) {
        LOGGER.error("Error occured when tried to update bucket");
        return bucketDao.get(bucketId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(long bucketId) {
        return bucketDao.delete(bucketId);
    }

    @Override
    public void addItem(Bucket bucket, Item item) {
        bucket.items.add(item);
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        bucket.items.remove(item);
    }

    @Override
    public void clear(Bucket bucket) {
        bucket.items.clear();
    }

    @Override
    public List<Item> getAllItems(Bucket bucket) {
        return bucket.items;
    }
}
