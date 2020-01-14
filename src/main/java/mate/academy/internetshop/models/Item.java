package mate.academy.internetshop.models;

public class Item {
    private Long id;
    private String name;
    private Double price;

    public Item(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Item setName(String name) {
        this.name = name;
        return this;
    }

    public Double getPrice() {
        return price;
    }

    public Item setPrice(Double price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "Item{"
                + "id="
                + id
                + ", name='"
                + name
                + '\''
                + ", price="
                + price
                + '}';
    }
}
