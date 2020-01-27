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

    public Bucket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Item> getAllItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Bucket{"
                + "id="
                + id
                + ", userId="
                + userId
                + ", items="
                + items
                + '}';
    }
}
