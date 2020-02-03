package mate.academy.internetshop.services.implementation;

import java.util.NoSuchElementException;
import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.exception.DataProcessingException;
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
    public Bucket create(Bucket bucket) throws DataProcessingException {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket get(long bucketId) {
        return bucketDao.get(bucketId).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Bucket update(Bucket bucket) throws DataProcessingException {
        return bucketDao.update(bucket);
    }

    @Override
    public boolean delete(long bucketId) throws DataProcessingException {
        return bucketDao.delete(bucketId);
    }

    @Override
    public boolean addItem(Bucket bucket, Item item) throws DataProcessingException {
        return bucketDao.addItemInBucket(item, bucket.getId());
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        bucket.items.remove(item);
    }
}
