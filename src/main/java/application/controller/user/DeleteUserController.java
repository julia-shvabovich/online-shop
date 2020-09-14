package application.controller.user;

import application.lib.Injector;
import application.model.ShoppingCart;
import application.service.ShoppingCartService;
import application.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/user/delete")
public class DeleteUserController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("application");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        userService.delete(id);
        ShoppingCart cart = shoppingCartService.getByUserId(id);
        shoppingCartService.delete(cart.getId());
        resp.sendRedirect(req.getContextPath() + "/user/all");
    }
}
