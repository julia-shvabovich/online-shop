package application.web.filter;

import application.lib.Injector;
import application.model.Role;
import application.model.User;
import application.service.UserService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {
    private static final Injector injector = Injector.getInstance("application");
    private static UserService userService = (UserService) injector.getInstance(UserService.class);
    private static final String USER_ID = "userId";
    private Map<String, Set<Role.RoleName>> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/admin", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/users/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/user/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/products", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/products/add", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/products/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/orders/delete", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/orders/all", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/admin/orders/details", Set.of(Role.RoleName.ADMIN));
        protectedUrls.put("/orders/create", Set.of(Role.RoleName.USER));
        protectedUrls.put("/orders/details", Set.of(Role.RoleName.USER));
        protectedUrls.put("/orders/history", Set.of(Role.RoleName.USER));
        protectedUrls.put("/cart/add-product", Set.of(Role.RoleName.USER));
        protectedUrls.put("/cart/delete", Set.of(Role.RoleName.USER));
        protectedUrls.put("/cart/products", Set.of(Role.RoleName.USER));
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String requestedUrl = req.getServletPath();
        if (protectedUrls.get(requestedUrl) == null) {
            filterChain.doFilter(req, resp);
            return;
        }
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        User user = userService.get(userId);
        if (isAuthorized(user, protectedUrls.get(requestedUrl))) {
            filterChain.doFilter(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/view/access-denied.jsp").forward(req, resp);
        }
    }

    @Override
    public void destroy() {
    }

    private boolean isAuthorized(User user, Set<Role.RoleName> authorizedRoles) {
        for (Role.RoleName authorizedRole: authorizedRoles) {
            for (Role userRole: user.getRoles()) {
                if (authorizedRole.equals(userRole.getRoleName())) {
                    return true;
                }
            }
        }
        return false;
    }
}
