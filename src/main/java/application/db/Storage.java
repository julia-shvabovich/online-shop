package application.db;

import application.model.Order;
import application.model.Product;
import application.model.ShoppingCart;
import application.model.User;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    public static List<Product> products = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static List<ShoppingCart> shoppingCarts = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static Long orderId = 0L;
    private static Long productId = 0L;
    private static Long cartId = 0L;
    private static Long userId = 0L;

    public static List<Order> getOrderList() {
        return orders;
    }

    public static List<Product> getProductList() {
        return products;
    }

    public static List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public static List<User> getUserList() {
        return users;
    }

    public static void addOrder(Order order) {
        order.setId(++orderId);
        orders.add(order);
    }

    public static void addProduct(Product product) {
        product.setId(++productId);
        products.add(product);
    }

    public static void addCart(ShoppingCart shoppingCart) {
        shoppingCart.setId(++cartId);
        shoppingCarts.add(shoppingCart);
    }

    public static void addUser(User user) {
        user.setId(++userId);
        users.add(user);
    }
}
