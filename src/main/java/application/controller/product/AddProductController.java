package application.controller.product;

import application.lib.Injector;
import application.model.Product;
import application.service.ProductService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/products/add")
public class AddProductController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("application");
    private ProductService productService =
            (ProductService) injector.getInstance(ProductService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/product/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        long price = Long.parseLong(req.getParameter("price"));
        productService.create(new Product(name, price));
        resp.sendRedirect(req.getContextPath() + "/products/add");
    }
}
