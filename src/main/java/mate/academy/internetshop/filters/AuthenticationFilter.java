package mate.academy.internetshop.filters;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class AuthenticationFilter implements Filter {
    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        Optional<Cookie> cookie = Arrays.stream(req.getCookies())
                .filter(x -> x.getName().equals("token"))
                .findFirst();
        if (cookie.isPresent()) {
            Optional<User> user = userService.findByToken(cookie.get().getValue());
            if (user.isPresent()) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }
        resp.sendRedirect(req.getContextPath() +"/login");
    }

    @Override
    public void destroy() {

    }
}
