package io.ride.service;

import io.ride.dao.RoleDao;
import io.ride.domain.Role;
import io.ride.util.DataSourceUtil;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午9:17
 */
public class RoleService {
    private RoleDao dao = new RoleDao();

    /**
     * 列出所有角色
     *
     * @return 所有角色
     * @throws SQLException 操作异常
     */
    public List<Role> listAll() throws SQLException {
        return dao.findAllRole();
    }

    /**
     * 根据ID查找角色
     *
     * @param id 带查找角色
     * @return 查找到的角色
     * @throws SQLException 操作异常
     */
    public Role find(String id) throws SQLException {
        return dao.findRoleById(id);
    }

    /**
     * 添加角色
     *
     * @param role         待添加角色
     * @param privilegeIds 角色拥有的权限
     * @throws SQLException 添加角色异常
     */
    public void add(Role role, String[] privilegeIds) throws SQLException {

        Connection conn = DataSourceUtil.getConnection();
        if (conn != null) {
            try {
                // 开启事务
                conn.setAutoCommit(false);
                // 添加角色
                dao.addRole(conn, role);
                // 设置ID
                int id = dao.getIdentity(conn);
                role.setId(id);
                // 添加权限
                dao.updateRolePrivilege(conn, role, privilegeIds);
            } catch (SQLException e) {
                try {
                    DbUtils.rollback(conn);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    throw new SQLException("[UserService.add] --> 回滚失败");
                }
                e.printStackTrace();
                throw new SQLException("[UserService.add] --> 添加角色失败");
            } finally {
                try {
                    DbUtils.commitAndClose(conn);
                } catch (SQLException e) {
                    throw new SQLException("[UserService.add] --> 添加角色失败");
                }
            }
        } else {
            throw new SQLException("[UserService.add] --> connection对象为空值");
        }
    }

    /**
     * 更新角色
     *
     * @param role         待更新的角色
     * @param privilegeIds 待更新的角色权限
     * @throws SQLException 更新角色异常
     */
    public void update(Role role, String[] privilegeIds) throws SQLException {
        Connection conn = DataSourceUtil.getConnection();
        if (conn != null) {
            try {
                // 开启事务
                conn.setAutoCommit(false);
                // 添加角色
                dao.updateRoleById(conn, role);
                // 清空权限
                dao.deleteAllPrivilege(conn, role);
                // 添加权限
                dao.updateRolePrivilege(conn, role, privilegeIds);
            } catch (SQLException e) {
                DbUtils.rollback(conn);
                throw new SQLException("[UserService.update] --> 更新角色失败");
            } finally {
                try {
                    DbUtils.commitAndClose(conn);
                } catch (SQLException e) {
                    throw new SQLException("[UserService.update] --> 更新角色失败");
                }
            }
        } else {
            throw new SQLException("[UserService.update] --> connection对象为空值");
        }
    }

    /**
     * 删除角色
     *
     * @param id 待删除角色ID
     * @throws SQLException 删除角色异常
     */
    public void delete(String id) throws SQLException {
        dao.deleteRoleById(id);
    }
}


