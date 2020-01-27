package mate.academy.internetshop.services;

import java.util.List;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;

public interface BucketService {

    public Bucket create(Bucket bucket) throws DataProcessingException;

    public Bucket get(long bucketId);

    public Bucket update(Bucket bucket) throws DataProcessingException;

    public boolean delete(long bucketId) throws DataProcessingException;

    public void addItem(Bucket bucket, Item item);

    public void deleteItem(Bucket bucket, Item item);

    public boolean clear(Bucket bucket) throws DataProcessingException;

    public List<Item> getAllItems(Bucket bucket);
}
