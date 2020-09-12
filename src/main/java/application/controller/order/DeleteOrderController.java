package application.controller.order;

import application.lib.Injector;
import application.service.OrderService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orders/delete")
public class DeleteOrderController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("application");
    private OrderService orderService = (OrderService) injector.getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        orderService.delete(id);
        resp.sendRedirect(req.getContextPath() + "/orders/all");
    }
}
