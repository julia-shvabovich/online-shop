package application.service;

import application.model.Product;

public interface ProductService extends GenericService<Product, Long> {
    Product update(Product product);
}
