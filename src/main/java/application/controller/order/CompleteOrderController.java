package application.controller.order;

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
    private static final Long USER_ID = 1L;
    private static final Injector injector = Injector.getInstance("application");
    private OrderService orderService =
            (OrderService) injector.getInstance(OrderService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        orderService.completeOrder(shoppingCartService.getByUserId(USER_ID));
        resp.sendRedirect(req.getContextPath() + "/order/history");
    }
}
