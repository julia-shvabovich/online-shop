package application.controller;

import application.lib.Injector;
import application.model.Role;
import application.model.User;
import application.service.UserService;
import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inject")
public class InjectDataController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("application");
    private UserService userService = (UserService) injector.getInstance(UserService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User admin = new User("admin", "admin", "12345");
        admin.setRoles(Set.of(Role.of("ADMIN")));
        userService.create(admin);
        resp.sendRedirect(req.getContextPath() + "/");
    }
}
