package mate.academy.internetshop.dao;

import java.util.Optional;

import mate.academy.internetshop.models.Bucket;

public interface BucketDao {

    Bucket create(Bucket bucket);

    Optional<Bucket> get(long bucketId);

    Bucket update(Bucket bucket);

    boolean delete(long bucketId);
}
