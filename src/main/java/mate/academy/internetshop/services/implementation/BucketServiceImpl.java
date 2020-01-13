package mate.academy.internetshop.services.implementation;

import java.util.List;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;
import mate.academy.internetshop.services.BucketService;

@Service
public class BucketServiceImpl implements BucketService {

    @Inject
    static BucketDao bucketDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(Long bucketId) {
        return bucketDao.get(bucketId).orElse(bucketDao.create(new Bucket()));
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(Long bucketId) {
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
