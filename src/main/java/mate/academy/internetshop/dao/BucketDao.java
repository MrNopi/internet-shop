package mate.academy.internetshop.dao;

import java.util.Optional;

import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Bucket;

public interface BucketDao {

    Bucket create(Bucket bucket) throws DataProcessingException;

    Optional<Bucket> get(Long bucketId);

    Bucket update(Bucket bucket) throws DataProcessingException;

    boolean delete(Long bucketId) throws DataProcessingException;

    public boolean clear(Long bucketId) throws DataProcessingException;
}
