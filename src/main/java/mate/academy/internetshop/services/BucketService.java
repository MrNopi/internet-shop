package mate.academy.internetshop.services;

import java.util.List;
import java.util.Optional;
import mate.academy.internetshop.models.Bucket;
import mate.academy.internetshop.models.Item;

public interface BucketService {

    public Bucket create(Bucket bucket);

    public Optional<Bucket> get(long bucketId);

    public Bucket update(Bucket bucket);

    public boolean delete(long bucketId);

    public void addItem(Bucket bucket, Item item);

    public void deleteItem(Bucket bucket, Item item);

    public void clear(Bucket bucket);

    public List<Item> getAllItems(Bucket bucket);
}
