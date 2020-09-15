package application.controller.order;

import application.controller.user.LoginController;
import application.lib.Injector;
import application.model.Order;
import application.service.OrderService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orders/history")
public class GetUserOrdersController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("application");
    private final OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = (Long) req.getSession().getAttribute(LoginController.USER_ID);
        List<Order> orders = orderService.getUserOrders(id);
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("/WEB-INF/view/order/history.jsp").forward(req, resp);
    }
}
