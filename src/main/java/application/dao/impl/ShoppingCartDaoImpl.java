package application.dao.impl;

import application.dao.ShoppingCartDao;
import application.db.Storage;
import application.lib.Dao;
import application.model.ShoppingCart;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Dao
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        Storage.addCart(shoppingCart);
        return shoppingCart;
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        return Storage.getShoppingCarts().stream()
                .filter(cart -> cart.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<ShoppingCart> getAll() {
        return Storage.getShoppingCarts();
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        IntStream.range(0, Storage.shoppingCarts.size())
                .filter(i -> Storage.shoppingCarts.get(i).getId().equals(shoppingCart.getId()))
                .forEach(i -> Storage.shoppingCarts.set(i, shoppingCart));
        return shoppingCart;
    }

    @Override
    public boolean delete(Long id) {
        return Storage.shoppingCarts
                .removeIf(c -> c.getId().equals(id));
    }

    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        return Storage.getShoppingCarts().stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst();
    }
}
