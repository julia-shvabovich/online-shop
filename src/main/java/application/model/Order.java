package application.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private Long id;
    private List<Product> products;
    private Long userId;

    public Order(long userId) {
        this.products = new ArrayList<>();
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
