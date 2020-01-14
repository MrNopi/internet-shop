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
        LOGGER.error("Error occured when tried to update bucket");
            Bucket buck = Storage.buckets
                    .stream()
                    .filter(x -> x.getId().equals(bucket.getId()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new);
            int index = Storage.buckets.indexOf(buck);
            return Storage.buckets.set(index, bucket);
    }

    @Override
    public boolean delete(long bucketId) {
        LOGGER.error("Error occured when tried to update bucket");
        return Storage.buckets.remove(Storage.buckets
                .stream()
                .filter(x -> x.getId().equals(bucketId))
                .findFirst()
                .orElseThrow(NoSuchElementException::new));
    }
}
