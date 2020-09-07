package application.service.impl;

import application.dao.ShoppingCartDao;
import application.db.Storage;
import application.lib.Inject;
import application.lib.Service;
import application.model.Product;
import application.model.ShoppingCart;
import application.service.ShoppingCartService;
import java.util.ArrayList;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDao.create(shoppingCart);
    }

    @Override
    public ShoppingCart addProduct(ShoppingCart shoppingCart, Product product) {
        return shoppingCartDao.addProduct(shoppingCart, product);
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        return shoppingCartDao.deleteProduct(shoppingCart, product);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        Storage.getShoppingCarts().stream()
                .filter(cart -> cart.equals(shoppingCart))
                .forEach(cart -> cart.setProducts(new ArrayList<>()));

    }

    @Override
    public ShoppingCart getByUserId(Long userId) {
        return shoppingCartDao.get(userId).get();
    }

    @Override
    public boolean delete(ShoppingCart shoppingCart) {
        return shoppingCartDao.delete(shoppingCart);
    }
}
