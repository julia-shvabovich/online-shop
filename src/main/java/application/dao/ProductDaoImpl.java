package application.dao;

import application.db.Storage;
import application.lib.Dao;
import application.model.Product;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoImpl implements ProductDao {

    @Override
    public Product create(Product product) {
        Storage.addProduct(product);
        return product;
    }

    @Override
    public Optional<Product> get(Long id) {
        return Storage.getProductList().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Product> getAll() {
        return Storage.getProductList();
    }

    @Override
    public Product update(Product product) {
        List<Product> products = Storage.getProductList();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                products.set(i, product);
            }
        }
        return product;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getProductList()
                .removeIf(product -> product.getId().equals(id));
    }
}
