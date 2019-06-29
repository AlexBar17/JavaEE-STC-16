package lesson22.task01.src.main.java.ru.inno.stc14.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

@WebFilter(filterName = "logFilter", urlPatterns = "/*")
public class LogFilter implements Filter {
    private Logger logger = Logger.getLogger(LogFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.warning("init logFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession(true);
        Enumeration<String> atts = session.getAttributeNames();
        while (atts.hasMoreElements()) {
            String att = atts.nextElement();
            System.out.println(att);
        }

        System.out.println();
        System.out.println("Хотели пойти сюда " + req.getRequestURI());
        System.out.println(req.getServletPath());

        if (!req.getRequestURI().equals("/students_war/person")) {
            if (session.getAttribute("chek") == null) {
                System.out.println("на переадресацию в /students_war/person");
                resp.sendRedirect("/students_war/person");
            }

        }

                filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        logger.warning("destroy logFilter");
    }
}
