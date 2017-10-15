package io.ride.filter;

import io.ride.exception.PrivilegeException;
import io.ride.util.PrivilegeUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 上午11:50
 */
public class PrivilegeFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("[Privilege Filter is Working..........]");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        req.setCharacterEncoding("utf-8");

        try {
            PrivilegeUtil.checkPrivilege(req);
            filterChain.doFilter(req, resp);

        } catch (PrivilegeException e) {
            String errorMsg = e.getMessage();
            req.setAttribute("msgError", errorMsg);
            req.getRequestDispatcher("/pages/msg.jsp").forward(req, resp);
        }

    }

    public void destroy() {

    }
}
