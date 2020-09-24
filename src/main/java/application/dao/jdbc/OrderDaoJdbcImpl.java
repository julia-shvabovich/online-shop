package application.dao.jdbc;

import application.dao.OrderDao;
import application.exceptions.DataProcessingException;
import application.lib.Dao;
import application.model.Order;
import application.model.Product;
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
public class OrderDaoJdbcImpl implements OrderDao {
    @Override
    public List<Order> getUserOrders(Long userId) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE user_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get orders for user with id " + userId, e);
        }
        for (Order order : orders) {
            order.setProducts(getProducts(order.getId()));
        }
        return orders;
    }

    @Override
    public Order create(Order order) {
        String query = "INSERT INTO orders(user_id) VALUES (?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't create order " + order, e);
        }
        return setProducts(order);
    }

    @Override
    public Optional<Order> get(Long id) {
        Order order = null;
        String query = "SELECT * FROM orders WHERE id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = getOrderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get order with id " + id, e);
        }
        order.setProducts(getProducts(order.getId()));
        return Optional.of(order);
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders WHERE deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(getOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get orders from DB", e);
        }
        for (Order order : orders) {
            order.setProducts(getProducts(order.getId()));
        }
        return orders;
    }

    @Override
    public Order update(Order order) {
        String query = "DELETE FROM orders_products WHERE order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't update order " + order, e);
        }
        return setProducts(order);
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE orders SET deleted = true WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() != 0;
        } catch (SQLException exception) {
            throw new DataProcessingException("Failed to delete the order "
                    + "with id: " + id, exception);
        }
    }

    private Order setProducts(Order order) {
        String query = "INSERT INTO orders_products(order_id, product_id) VALUES(?,?)";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, order.getId());
            List<Product> products = order.getProducts();
            for (Product product : products) {
                statement.setLong(2, product.getId());
                statement.executeUpdate();
            }
            return order;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't insert data to order with id "
                    + order.getId(), e);
        }
    }

    private List<Product> getProducts(Long orderId) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products p INNER JOIN orders_products op "
                + "ON p.id = op.product_id WHERE op.order_id = ?";
        try (Connection connection = ConnectionUtil.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long productId = resultSet.getLong("id");
                String productName = resultSet.getString("name");
                double productPrice = resultSet.getDouble("price");
                products.add(new Product(productId, productName, productPrice));
            }
            return products;
        } catch (SQLException e) {
            throw new DataProcessingException("Couldn't get products from the order with id "
                    + orderId, e);
        }
    }

    private Order getOrderFromResultSet(ResultSet rs) throws SQLException {
        Long orderId = rs.getLong("id");
        Long userId = rs.getLong("user_id");
        return new Order(orderId, userId);
    }
}
