package io.ride.dao;

import io.ride.domain.Privilege;
import io.ride.domain.Role;
import io.ride.util.DataSourceUtil;
import javafx.scene.chart.PieChart;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.management.Query;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午9:08
 */
public class RoleDao {


    /**
     * 查找所有角色
     *
     * @return 所有角色
     * @throws SQLException 查找异常
     */
    public List<Role> findAllRole() throws SQLException {
        String sql = "SELECT * FROM t_role;";
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
        List<Role> roles = runner.query(sql, new BeanListHandler<Role>(Role.class));

        if (roles == null) {
            throw new SQLException("[RoleDao.findAllRole] --> 查询为空");
        }

        sql = "Select p.* FROM t_role as r, t_privilege as p, t_role_privilege as rp " +
                "WHERE r.id = rp.role_id AND p.id = rp.privilege_id AND r.id = ? AND p.no_login='0';";

        for (Role role : roles) {
            List<Privilege> privileges = runner.query(sql, new BeanListHandler<Privilege>(Privilege.class), role.getId());
            role.setPrivileges(privileges);
        }

        return roles;
    }

    /**
     * 添加角色
     *
     * @param conn 数据库链接
     * @param role 待添加角色
     * @throws SQLException 添加异常
     */
    public void addRole(Connection conn, Role role) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "INSERT INTO t_role(role_name, description) VALUE(?,?)";
        int result = runner.update(conn, sql, role.getRole_name(), role.getDescription());
        if (result <= 0) {
            throw new SQLException("[RoleDao.addRole] --> 添加角色失败");
        }
    }

    /**
     * 根据角色ID查找角色
     *
     * @param id 角色ID
     * @return 要查找的角色
     * @throws SQLException 查询异常
     */
    public Role findRoleById(String id) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "SELECT * FROM t_role WHERE id = ?";
        Role role = runner.query(sql, new BeanHandler<Role>(Role.class), id);
        if (role == null) {
            throw new SQLException("[RoleDao.findRoleById] --> 查找角色失败");
        }
        sql = "Select p.* FROM t_role as r, t_privilege as p, t_role_privilege as rp " +
                "WHERE r.id = rp.role_id AND p.id = rp.privilege_id AND r.id = ?";
        List<Privilege> privileges = runner.query(sql, new BeanListHandler<Privilege>(Privilege.class), role.getId());
        role.setPrivileges(privileges);

        return role;
    }

    /**
     * 根据角色ID更新角色
     *
     * @param conn 数据库链接
     * @param role 角色
     * @throws SQLException 更新异常
     */
    public void updateRoleById(Connection conn, Role role) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "UPDATE t_role SET role_name = ?, description = ? WHERE id = ?;";
        int result = runner.update(conn, sql, role.getRole_name(), role.getDescription(), role.getId());
        if (result <= 0) {
            throw new SQLException("[RoleDao.updateRoleById] --> 更新角色失败");
        }
    }

    /**
     * 根据ID删除角色
     *
     * @param id 待删除角色ID
     * @throws SQLException 删除异常
     */
    public void deleteRoleById(String id) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "DELETE FROM t_role WHERE id = ?";
        int result = runner.update(sql, id);
        if (result <= 0) {
            throw new SQLException("[RoleDao.deleteRoleById] --> 查找角色失败");
        }
    }

    /**
     * 更新角色权限
     *
     * @param conn         数据库链接
     * @param role         待更新角色
     * @param privilegeIds 待更新权限ID
     * @throws SQLException 更新异常
     */
    public void updateRolePrivilege(Connection conn, Role role, String[] privilegeIds) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "INSERT INTO t_role_privilege(role_id, privilege_id) VALUES(?, ?)";
        Object[][] params = new Object[privilegeIds.length][2];
        for (int i = 0; i < privilegeIds.length; i++) {
            params[i][0] = role.getId();
            params[i][1] = privilegeIds[i];
        }
        int[] results = runner.batch(conn, sql, params);
        for (int result : results) {
            if (result <= 0) {
                throw new SQLException("[RoleDao.updateRolePrivilege] --> 更新角色权限失败");

            }
        }
    }

    /**
     * 删除角色所有的权限
     *
     * @param conn 数据库链接
     * @param role 待删除权限的角色
     * @throws SQLException 删除权限异常
     */
    public void deleteAllPrivilege(Connection conn, Role role) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "DELETE FROM t_role_privilege WHERE role_id = ?";
        int result = runner.update(conn, sql, role.getId());
        if (result <= 0) {
            throw new SQLException("[RoleDao.deleteAllPrivilege] --> 删除角色权限失败");
        }
    }

    /**
     * 获取最近一次插入中自增列的值
     *
     * @param conn 数据库链接
     * @return 最近一次插入中自增列的值
     * @throws SQLException 获取到空值
     */
    public Integer getIdentity(Connection conn) throws SQLException {
        String sql = "SELECT @@IDENTITY";

        QueryRunner runner = new QueryRunner();
        BigInteger result = runner.query(conn, sql, new ScalarHandler<BigInteger>());
        if (result == null) {
            throw new SQLException("[RoleDao.getIdentity] --> 空值");
        }
        return result.intValue();
    }

}
