package mate.academy.internetshop.dao.implementation;

import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.models.Bucket;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoImpl implements BucketDao {
    private static Long id = 0L;
    private static final Logger LOGGER = Logger.getLogger(BucketDaoImpl.class);

    @Override
    public Bucket create(Bucket bucket) {
        bucket.setId(++id);
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Optional<Bucket> get(long bucketId) {
        return Storage.buckets
                .stream()
                .filter(x -> x.getId().equals(bucketId))
                .findFirst();
    }

    @Override
    public Bucket update(Bucket bucket) {
        Bucket buck = null;
        try {
            buck = Storage.buckets
                    .stream()
                    .filter(x -> x.getId().equals(bucket.getId()))
                    .findFirst()
                    .get();
            int index = Storage.buckets.indexOf(buck);
            return Storage.buckets.set(index, bucket);
        } catch (NoSuchElementException e) {
            LOGGER.error("There is no such an element in storage");
        }
        return buck;
    }

    @Override
    public boolean delete(long bucketId) {
        boolean isRemoved = false;
        try {
            isRemoved = Storage.buckets.remove(Storage.buckets
                    .stream()
                    .filter(x -> x.getId().equals(bucketId))
                    .findFirst()
                    .get());
        } catch (NoSuchElementException e) {
            LOGGER.error("There is no such an element in storage");
        }
        return isRemoved;
    }
}
