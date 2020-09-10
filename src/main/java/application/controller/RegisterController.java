package application.controller;

import application.lib.Injector;
import application.model.ShoppingCart;
import application.model.User;
import application.service.ShoppingCartService;
import application.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class RegisterController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("application");
    private UserService userService = (UserService) injector.getInstance(UserService.class);
    private ShoppingCartService shoppingCartService =
            (ShoppingCartService) injector.getInstance(ShoppingCartService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/user/registration.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeat");
        if (password.equals(repeatPassword)) {
            User user = new User(name, login, password);
            userService.create(user);
            shoppingCartService.create(new ShoppingCart(user.getId()));
            resp.sendRedirect(req.getContextPath() + "/");
        } else {
            req.setAttribute("message", "Your password and repeated password aren't the same");
            req.getRequestDispatcher("/WEB-INF/view/user/registration.jsp").forward(req, resp);
        }
    }
}
