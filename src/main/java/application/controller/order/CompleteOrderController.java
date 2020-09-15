package application.controller.order;

import application.controller.user.LoginController;
import application.lib.Injector;
import application.service.OrderService;
import application.service.ShoppingCartService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orders/create")
public class CompleteOrderController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("application");
    private OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        orderService.completeOrder(shoppingCartService.getByUserId(id));
        resp.sendRedirect(req.getContextPath() + "/order/history");
    }
}
