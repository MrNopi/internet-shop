package mate.academy.internetshop.filters;

import java.io.IOException;
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
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.UserService;
import org.apache.log4j.Logger;

@WebFilter("/Servlet/*")
public class AuthenticationFilter implements Filter {
    @Inject
    private static UserService userService;
    private static final Logger LOGGER = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String token = (String) req.getSession(true).getAttribute("token");
        if (token != null) {
            Optional<User> user;
            try {
                user = userService.findByToken(token);
            } catch (DataProcessingException e) {
                LOGGER.error(e);
                req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                return;
            }
            if (user.isPresent()) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    public void destroy() {

    }
}
