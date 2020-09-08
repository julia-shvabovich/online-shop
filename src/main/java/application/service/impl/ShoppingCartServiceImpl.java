package application.service.impl;

import application.dao.ShoppingCartDao;
import application.db.Storage;
import application.lib.Inject;
import application.lib.Service;
import application.model.Product;
import application.model.ShoppingCart;
import application.service.ShoppingCartService;

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
        Storage.getShoppingCarts().stream()
                .filter(cart -> cart.equals(shoppingCart))
                .forEach(cart -> cart.getProducts().add(product));
        return shoppingCart;
    }

    @Override
    public boolean deleteProduct(ShoppingCart shoppingCart, Product product) {
        Storage.getShoppingCarts().stream()
                .filter(cart -> cart.equals(shoppingCart))
                .forEach(cart -> cart.getProducts().remove(product));
        return true;
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getProducts().clear();
        shoppingCartDao.update(shoppingCart);
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
