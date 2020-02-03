package mate.academy.internetshop.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private Long userId;
    private List<Item> items = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public Order setItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id="
                + id
                + ", userId="
                + userId
                + ", items="
                + items
                + '}';
    }
}
