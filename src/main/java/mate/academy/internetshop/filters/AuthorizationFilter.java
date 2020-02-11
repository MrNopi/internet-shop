package mate.academy.internetshop.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.exception.DataProcessingException;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Role;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.UserService;

@WebFilter("/Servlet/*")
public class AuthorizationFilter implements Filter {
    @Inject
    private static UserService userService;
    private Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/Servlet/Users", Role.RoleName.ADMIN);
        protectedUrls.put("/Servlet/createItem", Role.RoleName.ADMIN);
        protectedUrls.put("/Servlet/deleteItem", Role.RoleName.ADMIN);

        protectedUrls.put("/Servlet/addToBucket", Role.RoleName.USER);
        protectedUrls.put("/Servlet/deleteFromBucket", Role.RoleName.USER);
        protectedUrls.put("/Servlet/ShowBucketItems", Role.RoleName.USER);
        protectedUrls.put("/Servlet/CompleteOrder", Role.RoleName.USER);
        protectedUrls.put("/Servlet/ShowAllOrders", Role.RoleName.USER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Role.RoleName neededRole = protectedUrls.get(req.getServletPath());
        String token = (String)req.getSession(true).getAttribute("token");
        if (token != null) {
            Optional<User> user;
            try {
                user = userService.findByToken(token);
            } catch (DataProcessingException e) {

                req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                return;
            }
            if (user.isPresent()) {
                authorized(user.get(), neededRole, req, resp, filterChain);
                return;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/Servlet/login");
    }

    @Override
    public void destroy() {
    }

    private void authorized(User user, Role.RoleName role, HttpServletRequest req,
                            HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        boolean isAuthorized = user.getRole().stream()
                .anyMatch(x -> x.getRole().equals(role));
        if (isAuthorized || role == null) {
            chain.doFilter(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/Servlet/PermissionDenied");
        }
    }
}
