package application.controller.order;

import application.lib.Injector;
import application.model.Product;
import application.service.OrderService;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/orders/details")
public class GetOrderDetailsController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("application");
    private final OrderService orderService = (OrderService) injector
            .getInstance(OrderService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String orderId = req.getParameter("id");
        Long id = Long.valueOf(orderId);
        List<Product> productsInOrder = orderService.get(id).getProducts();
        req.setAttribute("products", productsInOrder);
        req.getRequestDispatcher("/WEB-INF/view/order/details.jsp").forward(req, resp);
    }
}
