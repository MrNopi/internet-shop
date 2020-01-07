package mate.academy.internetshop.models;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private static User user;
    public List<Item> items = new ArrayList<>();

    public Bucket(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Bucket setId(long id) {
        this.id = id;
        return this;
    }
}
