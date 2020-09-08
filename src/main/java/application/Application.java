package application;

import application.lib.Injector;
import application.model.Order;
import application.model.Product;
import application.model.ShoppingCart;
import application.model.User;
import application.service.OrderService;
import application.service.ProductService;
import application.service.ShoppingCartService;
import application.service.UserService;
import java.util.NoSuchElementException;

public class Application {
    private static Injector injector = Injector.getInstance("application");

    public static void main(String[] args) {
        ProductService productService = (ProductService) injector.getInstance(ProductService.class);
        System.out.println("Creating products:");
        Product cat = new Product("Cat", 300);
        Product dog = new Product("Dog", 150);
        Product julia = new Product("Julia", 1);
        productService.create(cat);
        productService.create(dog);
        productService.create(julia);
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }

        System.out.println("Updating products:");
        Product newCat = new Product("Cat", 270);
        newCat.setId(cat.getId());
        productService.update(newCat);
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }

        System.out.println("Deleting products:");
        productService.delete(julia.getId());
        for (Product product : productService.getAll()) {
            System.out.println(product);
        }

        UserService userService = (UserService) injector.getInstance(UserService.class);
        System.out.println("Creating users:");
        User firstUser = new User("Tatiana", "tatiana_schwarz", "1111");
        User secondUser = new User("Zoie", "Zoie_Nikitina", "Stepanych1love");
        userService.create(firstUser);
        userService.create(secondUser);
        for (User user : userService.getAll()) {
            System.out.println(user);
        }

        System.out.println("Changing password:");
        User newUser = new User("Zoie", "Zoie_Nikitina", "StepanychAsshole");
        newUser.setId(2L);
        userService.update(newUser);
        System.out.println(userService.get(2L));

        System.out.println("Deleting user:");
        userService.delete(2L);
        try {
            System.out.println(userService.get(2L));
        } catch (NoSuchElementException exception) {
            System.out.println("The user does not exist");
        }

        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        System.out.println("Creating shopping carts:");
        userService.create(secondUser);
        ShoppingCart firstCart = new ShoppingCart(1);
        ShoppingCart secondCart = new ShoppingCart(2);
        shoppingCartService.create(firstCart);
        shoppingCartService.create(secondCart);
        System.out.println(firstCart);
        System.out.println(secondCart);

        System.out.println("Adding products to existing carts:");
        shoppingCartService.addProduct(firstCart, cat);
        shoppingCartService.addProduct(firstCart, julia);
        shoppingCartService.addProduct(secondCart, dog);
        System.out.println(firstCart);
        System.out.println(secondCart);

        System.out.println("Clearing cart:");
        shoppingCartService.clear(secondCart);
        System.out.println(secondCart);

        System.out.println("Deleting cart:");
        shoppingCartService.delete(secondCart);
        try {
            System.out.println(shoppingCartService.getByUserId(2L));
        } catch (NoSuchElementException exception) {
            System.out.println("The cart does not exist");
        }

        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        System.out.println("Completing orders:");
        Order order = orderService.completeOrder(firstCart);
        System.out.println(order);

        System.out.println("Getting orders:");
        System.out.println(orderService.getUserOrders(1L));

        System.out.println("Deleting orders:");
        orderService.delete(1L);
        System.out.println(orderService.getUserOrders(1L));
    }
}
