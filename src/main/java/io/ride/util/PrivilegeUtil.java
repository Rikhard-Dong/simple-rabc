package io.ride.util;

import io.ride.domain.User;
import io.ride.exception.PrivilegeException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午12:04
 */
public class PrivilegeUtil {
    public static void checkPrivilege(HttpServletRequest request) throws PrivilegeException {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        String requestUrl = request.getRequestURI();

        System.out.println("[当前登录用户] --> " + (loginUser == null ? "不存在的" : loginUser.getNickname()));
        System.out.println("[当前URL] --> " + requestUrl);

        try {
            if (!isAllowNoLogin(requestUrl)) {
                isRoleAllow(loginUser, requestUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void isRoleAllow(User loginUser, String requestUrl) throws SQLException, PrivilegeException {
        // 查找拥有当前url和action权限的所有角色ID
        String sql = "SELECT r.id FROM t_role AS r, t_privilege AS p, t_role_privilege AS rp " +
                "WHERE r.id = rp.role_id AND p.id = rp.privilege_id " +
                "AND p.privilege_url = ?;";
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
        // 获取所有合法的角色ID
        List<Object> legalRoleIds = runner.query(sql, new ColumnListHandler<Object>(1), requestUrl);

        if (legalRoleIds != null) {
            sql = "SELECT r.id FROM t_role AS r, t_user AS u, t_user_role AS ur" +
                    " WHERE r.id = ur.role_id AND u.id = ur.user_id AND u.id = ?;";
            // 获取该用户的所有角色ID
            List<Object> userRoleIds = runner.query(sql, new ColumnListHandler<Object>(1), loginUser.getId());

            System.out.println("[合法角色ID] --> " + legalRoleIds);
            System.out.println("[用户角色ID] --> " + userRoleIds);

            legalRoleIds.retainAll(userRoleIds);
            if (legalRoleIds.size() <= 0) {
                throw new PrivilegeException("权限不足");
            }
        }
    }

    private static boolean isAllowNoLogin(String url) throws SQLException, PrivilegeException {
        String sql = "SELECT COUNT(*) FROM t_privilege WHERE privilege_url = ? AND no_login = '1'";
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());

        Long result = runner.query(sql, new ScalarHandler<Long>(), url);
        System.out.println("[当前网页是否可以未登录访问] --> " + (result == 1 ? "是" : "否 "));
        if (result < 1) {
            return false;
        } else {
            return true;
        }
    }
}
