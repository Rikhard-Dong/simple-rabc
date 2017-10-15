package io.ride.servlet;

import io.ride.domain.Privilege;
import io.ride.service.PrivilegeService;
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
 * Time: 下午11:30
 */
@WebServlet("/privilege/manage")
public class PrivilegeManageServlet extends HttpServlet {
    private PrivilegeService privilegeService = new PrivilegeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf8");

        String action = req.getParameter("action");
        System.out.println("[当前请求动作]  --> " + action);

        if ("list_privilege".equals(action)) {
            listAll(req, resp);
        } else if ("add_privilege_UI".equals(action)) {
            addPrivilegeUI(req, resp);
        } else if ("add_privilege".equals(action)) {
            addPrivilege(req, resp);
        } else if ("update_privilege_UI".equals(action)) {
            updatePrivilegeUI(req, resp);
        } else if ("update_privilege".equals(action)) {
            updatePrivilege(req, resp);
        } else if ("delete_privilege".equals(action)) {
            deletePrivilege(req, resp);
        } else {
            msgError(req, resp, "非法请求!");
        }
    }

    private void msgError(HttpServletRequest req, HttpServletResponse resp, String msg) throws ServletException, IOException {
        req.setAttribute("msgError", msg);
        req.getRequestDispatcher("/pages/msg.jsp").forward(req, resp);
    }

    private void deletePrivilege(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String privilegeId = req.getParameter("privilege_id");
        try {
            privilegeService.delete(privilegeId);
            req.getRequestDispatcher(req.getContextPath() + "/privilege/manage?action=list_privilege").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "删除权限失败");
        }
    }

    private void updatePrivilege(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String privilegeId = req.getParameter("privilege_id");
        Map<String, String[]> params = req.getParameterMap();
        Privilege privilege = new Privilege();

        // 打印获取到的参数
        System.out.println("[从表单中获取到的参数Map] -- >");
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            System.out.println("[键] --> " + entry.getKey() + " \t[值] --> " + Arrays.toString(entry.getValue()));
        }
        try {
            BeanUtils.populate(privilege, params);
            privilege.setId(Integer.valueOf(privilegeId));
            // 打印封装数据
            System.out.println("[数据封装结果] --> " + privilege);
            privilegeService.update(privilege);
            req.getRequestDispatcher("/privilege/manage?action=list_privilege").forward(req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "更新权限失败");
        }
    }

    private void updatePrivilegeUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String privilegeId = req.getParameter("privilege_id");
        try {
            Privilege privilege = privilegeService.find(privilegeId);
            req.setAttribute("privilege", privilege);
            req.getRequestDispatcher(req.getContextPath() + "/pages/privilege/manage/privilege_form.jsp?privilege_id="
                    + privilegeId).forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "权限查询失败");
        }
    }

    private void addPrivilege(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> params = req.getParameterMap();
        Privilege privilege = new Privilege();

        // 打印获取到的参数
        System.out.println("[从表单中获取到的参数Map] -- >");
        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            System.out.println("[键] --> " + entry.getKey() + " \t[值] --> " + Arrays.toString(entry.getValue()));
        }

        try {
            BeanUtils.populate(privilege, params);
            // 打印封装数据
            System.out.println("[数据封装结果] --> " + privilege);
            privilegeService.add(privilege);
            req.getRequestDispatcher("/privilege/manage?action=list_privilege").forward(req, resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            msgError(req, resp, "添加权限失败");
        }
    }

    private void addPrivilegeUI(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/pages/privilege/manage/privilege_form.jsp").forward(req, resp);
    }


    private void listAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Privilege> privileges = privilegeService.listAll();
            req.setAttribute("privilegeList", privileges);
            req.getRequestDispatcher("/pages/privilege/manage/privilege_manage.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


}
