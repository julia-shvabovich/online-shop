package application.controller.cart;

import application.controller.user.LoginController;
import application.lib.Injector;
import application.model.Product;
import application.model.ShoppingCart;
import application.service.ShoppingCartService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cart/products")
public class GetShoppingCartController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("application");
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        ShoppingCart cart = shoppingCartService.getByUserId(id);
        List<Product> allProducts = cart.getProducts();
        req.setAttribute("products", allProducts);
        req.getRequestDispatcher("/WEB-INF/view/cart/all.jsp").forward(req, resp);
    }
}
