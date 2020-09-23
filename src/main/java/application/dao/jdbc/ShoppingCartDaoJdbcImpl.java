package application.dao.jdbc;

import application.dao.ShoppingCartDao;
import application.exceptions.DataProcessingException;
import application.lib.Dao;
import application.model.Product;
import application.model.ShoppingCart;
import application.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dao
public class ShoppingCartDaoJdbcImpl implements ShoppingCartDao {
    @Override
    public Optional<ShoppingCart> getByUserId(Long userId) {
        ShoppingCart shoppingCart = null;
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "SELECT * FROM shopping_carts WHERE user_id = ?")) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                shoppingCart = getCartFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get shopping cart for user with id "
                    + userId, e);
        }
        shoppingCart.setProducts(getProducts(shoppingCart.getId()));
        return Optional.of(shoppingCart);
    }

    @Override
    public ShoppingCart create(ShoppingCart cart) {
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO shopping_carts(user_id) VALUES (?)",
                         Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, cart.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                cart.setId(resultSet.getLong(1));
            }
            return cart;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't create cart " + cart, e);
        }
    }

    @Override
    public Optional<ShoppingCart> get(Long id) {
        ShoppingCart cart = null;
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM shopping_carts WHERE deleted = FALSE AND id = ?")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cart = getCartFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get cart with id " + id, e);
        }
        cart.setProducts(getProducts(cart.getId()));
        return Optional.of(cart);
    }

    @Override
    public List<ShoppingCart> getAll() {
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM shopping_carts WHERE deleted = FALSE")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                shoppingCarts.add(getCartFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get carts from DB", e);
        }
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCart.setProducts(getProducts(shoppingCart.getId()));
        }
        return shoppingCarts;
    }

    @Override
    public ShoppingCart update(ShoppingCart cart) {
        Long cartId = cart.getId();
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "UPDATE shopping_carts SET user_id = ? WHERE id = ? "
                             + "AND deleted = false")) {
            statement.setLong(1, cart.getUserId());
            statement.setLong(2, cartId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't update cart " + cart, e);
        }
        String queryToDeleteProducts = "DELETE FROM shopping_carts_products WHERE cart_id = ?;";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(queryToDeleteProducts)) {
            statement.setLong(1, cart.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete products from shopping cart "
                    + cart, e);
        }
        return setProducts(cart);
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "UPDATE shopping_carts SET deleted = true WHERE id = ?")) {
            statement.setLong(1, id);
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't delete cart with id " + id, e);
        }
    }

    private ShoppingCart setProducts(ShoppingCart cart) {
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO shopping_carts_products(cart_id, product_id) "
                             + "VALUES(?, ?)")) {
            List<Product> products = cart.getProducts();
            statement.setLong(1, cart.getId());
            for (Product product : products) {
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
            return cart;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't update shopping cart " + cart, e);
        }
    }

    private List<Product> getProducts(Long cartId) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM products p LEFT JOIN shopping_carts_products scp "
                             + "ON p.id = scp.product_id WHERE scp.cart_id = ?")) {
            statement.setLong(1, cartId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("id");
                String productName = resultSet.getString("name");
                double productPrice = resultSet.getDouble("price");
                products.add(new Product(productId, productName, productPrice));
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get products from shopping cart with id "
                    + cartId, e);
        }
    }

    private ShoppingCart getCartFromResultSet(ResultSet resultSet) throws SQLException {
        Long cartId = resultSet.getLong("id");
        Long userId = resultSet.getLong("user_id");
        return new ShoppingCart(cartId, userId);
    }
}
