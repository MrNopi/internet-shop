package mate.academy.internetshop.models;

import java.util.ArrayList;
import java.util.List;

public class Bucket {
    private Long id;
    private Long userId;
    public List<Item> items = new ArrayList<>();

    public Bucket(User user) {
        this.userId = user.getId();
    }

    public void setUser(User user) {
        this.userId = user.getId();
    }

    public Long getId() {
        return id;
    }

    public Bucket setId(long id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "id=" + id +
                ", userId=" + userId +
                ", items=" + items +
                '}';
    }
}
