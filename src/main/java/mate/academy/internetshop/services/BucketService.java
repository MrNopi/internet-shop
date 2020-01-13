package mate.academy.internetshop.services;

import java.util.List;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;

public interface BucketService {

    public Bucket create(Bucket bucket);

    public Bucket get(Long bucketId);

    public Bucket update(Bucket bucket);

    public boolean delete(Long bucketId);

    public void addItem(Bucket bucket, Item item);

    public void deleteItem(Bucket bucket, Item item);

    public void clear(Bucket bucket);

    public List<Item> getAllItems(Bucket bucket);
}
