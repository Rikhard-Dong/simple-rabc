package io.ride.servlet;

import io.ride.domain.Privilege;
import io.ride.domain.Role;
import io.ride.service.PrivilegeService;
import io.ride.service.RoleService;
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
import java.util.List;
import java.util.Map;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-13
 * Time: 下午11:00
 */
@WebServlet("/role/manage")
public class RoleManageService extends HttpServlet {
    private RoleService roleService = new RoleService();
    private PrivilegeService privilegeService = new PrivilegeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf8");

        String action = req.getParameter("action");
        System.out.println("[当前请求动作]  --> " + action);

        if ("list_role".equals(action)) {
            listRole(req, resp);
        } else if ("add_role_UI".equals(action)) {
            addRoleUI(req, resp);
        } else if ("add_role".equals(action)) {
            addRole(req, resp);
        } else if ("update_role_UI".equals(action)) {
            updateRoleUI(req, resp);
        } else if ("update_role".equals(action)) {
            updateRole(req, resp);
        } else if ("delete_role".equals(action)) {
            deleteRole(req, resp);
        } else {
            msgError(req, resp, "非法请求动作!");
        }
    }

    private void addRoleUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Privilege> privileges = privilegeService.listAllLogin();
            req.setAttribute("privileges", privileges);
            req.getRequestDispatcher(req.getContextPath() + "/pages/role/manage/role_form.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "没有获取到权限信息");
        }

    }

    private void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        // 打印获取到的参数
        System.out.println("[从表单中获取到的参数Map] -- >");
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            System.out.println("[键] --> " + entry.getKey() + " \t[值] --> " + Arrays.toString(entry.getValue()));
        }

        Role role = new Role();
        try {
            BeanUtils.populate(role, params);
            String[] privilegeIds = req.getParameterValues("privilege_id");
            System.out.println("[赋予权限] --> " + Arrays.toString(privilegeIds));
            roleService.add(role, privilegeIds);
            req.getRequestDispatcher(req.getContextPath() + "/role/manage?action=list_role").forward(req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRoleUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleId = req.getParameter("role_id");
        try {
            Role role = roleService.find(roleId);
            List<Privilege> privileges = privilegeService.listAllLogin();
            req.setAttribute("role", role);
            System.out.println(role.getPrivileges());
            req.setAttribute("privileges", privileges);
            req.getRequestDispatcher(req.getContextPath() + "/pages/role/manage/role_form.jsp?role_id="
                    + roleId).forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "查找角色失败或者查询权限信息失败");
        }
    }

    private void updateRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleId = req.getParameter("role_id");
        Map<String, String[]> params = req.getParameterMap();
        // 打印获取到的参数
        System.out.println("[从表单中获取到的参数Map] -- >");
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            System.out.println("[键] --> " + entry.getKey() + " \t[值] --> " + Arrays.toString(entry.getValue()));
        }
        try {
            Role role = new Role();
            BeanUtils.populate(role, params);
            role.setId(Integer.valueOf(roleId));
            String[] privileges = req.getParameterValues("privilege_id");
            roleService.update(role, privileges);
            req.getRequestDispatcher(req.getContextPath() + "/role/manage?action=list_role").forward(req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "更新角色失败");
        }
    }

    private void deleteRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleId = req.getParameter("role_id");
        try {
            roleService.delete(roleId);
            req.getRequestDispatcher(req.getContextPath() + "/role/manage?action=list_role").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "删除用户失败");
        }
    }

    private void msgError(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException {
        req.setAttribute("msgError", msg);
        req.getRequestDispatcher("/pages/esg.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void listRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Role> roles = roleService.listAll();
            req.setAttribute("roleList", roles);
            req.getRequestDispatcher("/pages/role/manage/role_manage.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
