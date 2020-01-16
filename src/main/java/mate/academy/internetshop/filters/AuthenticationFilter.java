package mate.academy.internetshop.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.UserService;

public class AuthenticationFilter implements Filter {
    @Inject
    private static UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Optional<Cookie> cookie = Arrays.stream(req.getCookies())
                .filter(x -> x.getName().equals("token"))
                .findFirst();
        if (cookie.isPresent()) {
            Optional<User> user = userService.findByToken(cookie.get().getValue());
            if (user.isPresent()) {
                //req.getSession().setAttribute("userId", user.get().getId());
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }

    }

    @Override
    public void destroy() {

    }
}
