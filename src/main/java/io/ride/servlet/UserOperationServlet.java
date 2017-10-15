package io.ride.servlet;

import com.sun.nio.sctp.IllegalReceiveException;
import io.ride.domain.User;
import io.ride.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午2:07
 */
@WebServlet("/user/operation")
public class UserOperationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        String action = req.getParameter("action");
        System.out.println("[当前动作] --> " + action);

        try {
            if (action.equals("login")) {
                login(req, resp);
            } else if (action.equals("exit")) {
                exit(req, resp);
            } else {
                throw new IllegalReceiveException("请求操作错误!");
            }
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("loginUser");
        request.setAttribute("msgNotice", "你已登出");
        request.getRequestDispatcher("/pages/msg.jsp").forward(request, response);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException,
            IllegalAccessException, IOException, ServletException {
        User commitUser = new User();
        Map<String, String[]> param = req.getParameterMap();

        // 查看表单提交内容
        System.out.println("[表单提交内容]");
        for (Map.Entry<String, String[]> entry : param.entrySet()) {
            System.out.println("[key]--> " + entry.getKey() + "\t[value]--> " + Arrays.toString(entry.getValue()));
        }

        // 绑定bean的属性
        BeanUtils.populate(commitUser, param);
        System.out.println(commitUser);

        UserService userService = new UserService();
        User dbUser;
        try {
            dbUser = userService.login(commitUser);
            req.getSession().setAttribute("loginUser", dbUser);
            resp.sendRedirect(req.getContextPath() + "/");
        } catch (SQLException e) {
            req.getRequestDispatcher("/pages/user/operation/login.jsp").forward(req, resp);
        }
    }

}
