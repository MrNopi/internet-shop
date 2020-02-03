package mate.academy.internetshop.services;

import java.util.List;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;

public interface BucketService {

    Bucket create(Bucket bucket) throws DataProcessingException;

    Bucket get(long bucketId);

    Bucket update(Bucket bucket) throws DataProcessingException;

    boolean delete(long bucketId) throws DataProcessingException;

    boolean addItem(Bucket bucket, Item item) throws DataProcessingException;

    void deleteItem(Bucket bucket, Item item);
}
