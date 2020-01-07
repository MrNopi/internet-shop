package mate.academy.internetshop.models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    User user;
    List<Item> items = new ArrayList<>();

    public Order(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
