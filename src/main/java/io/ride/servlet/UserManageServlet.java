package io.ride.servlet;

import io.ride.domain.Role;
import io.ride.domain.User;
import io.ride.service.RoleService;
import io.ride.service.UserManageService;
import io.ride.util.RandomIdUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 上午11:25
 */
@WebServlet("/user/manage")
public class UserManageServlet extends HttpServlet {

    private UserManageService userManageService = new UserManageService();
    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf8");

        String action = req.getParameter("action");
        System.out.println("[当前请求动作]  --> " + action);

        if ("list_user".equals(action)) {
            listUser(req, resp);
        } else if (("add_user_UI").equals(action)) {
            addUserUI(req, resp);
        } else if ("add_user".equals(action)) {
            addUser(req, resp);
        } else if ("update_user_UI".equals(action)) {
            updateUserUI(req, resp);
        } else if ("update_user".equals(action)) {
            updateUser(req, resp);
        } else if ("delete_user".equals(action)) {
            deleteUser(req, resp);
        } else {
            msgError(req, resp, "请求出错");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void listUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<User> users = userManageService.listAll();
            req.setAttribute("userList", users);
            req.getRequestDispatcher("/pages/user/manage/user_manage.jsp").forward(req, resp);
        } catch (SQLException e) {
            msgError(req, resp, "list_user动作失败");
        }
    }

    private void addUserUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Role> roles = roleService.listAll();
            req.setAttribute("roles", roles);
            req.getRequestDispatcher(req.getContextPath() + "/pages/user/manage/user_form.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "打开添加用户页面异常");
        }

    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        String[] roleIds = req.getParameterValues("role_id");
        User user = new User();
        try {
            BeanUtils.populate(user, params);
            user.setId(RandomIdUtil.getRandomId(user.getEmail()));
            userManageService.addUser(user, roleIds);
            req.getRequestDispatcher(req.getContextPath() + "/user/manage?action=list_user").forward(req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "添加用户失败");
        }

    }

    private void updateUserUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String userId = req.getParameter("user_id");
            User user = userManageService.findUser(userId);
            System.out.println("[获得到的User] ------------------------------> " + user.toString());
            List<Role> roles = roleService.listAll();
            req.setAttribute("roles", roles);
            req.setAttribute("user", user);
            req.getRequestDispatcher(req.getContextPath() + "/pages/user/manage/user_form.jsp?user_id="
                    + userId).forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "打开更新用户页面异常");
        }
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        String[] roleIds = req.getParameterValues("role_id");
        String userId = req.getParameter("user_id");

        try {
            User user = userManageService.findUser(userId);
            BeanUtils.populate(user, params);
            System.out.println("[获得到的User] ------------------------------> " + user.toString());
            userManageService.updateUser(user, roleIds, false);
            req.getRequestDispatcher(req.getContextPath() + "/user/manage?action=list_user").forward(req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "更新用户失败");
        }
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("user_id");
        try {
            userManageService.deleteUser(userId);
            req.getRequestDispatcher(req.getContextPath() + "/user/manage?action=list_user").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "删除用户失败!");
        }
    }


    private void msgError(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException {
        req.setAttribute("msgError", msg);
        req.getRequestDispatcher("/pages/msg.jsp").forward(req, resp);
    }


}
