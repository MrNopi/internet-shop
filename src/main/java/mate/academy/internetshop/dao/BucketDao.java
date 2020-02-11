package mate.academy.internetshop.dao;

import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;

public interface BucketDao extends GenericDao<Bucket> {

    boolean clear(Long bucketId) throws DataProcessingException;

    boolean addItemInBucket(Item item, Long bucketId) throws DataProcessingException;
}
