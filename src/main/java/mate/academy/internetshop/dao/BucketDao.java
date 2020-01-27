package mate.academy.internetshop.dao;

import java.util.Optional;

import mate.academy.internetshop.models.Bucket;

public interface BucketDao {

    Bucket create(Bucket bucket);

    Optional<Bucket> get(Long bucketId);

    Bucket update(Bucket bucket);

    boolean delete(Long bucketId);

    public boolean clear(Long bucketId);
}
