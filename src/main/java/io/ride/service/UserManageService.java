package io.ride.service;

import io.ride.dao.UserManageDao;
import io.ride.domain.User;
import io.ride.util.DataSourceUtil;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午7:25
 */
public class UserManageService {
    private UserManageDao dao = new UserManageDao();

    public List<User> listAll() throws SQLException {
        return dao.findAllUser();
    }

    public void addUser(User user, String[] roleIds) throws SQLException {
        // 使用事务
        Connection conn = DataSourceUtil.getConnection();
        if (conn != null) {
            conn.setAutoCommit(false);
            try {
                dao.addUser(conn, user);
                dao.updateUserRole(conn, user, roleIds);
            } catch (SQLException e) {

                DbUtils.rollback(conn);
                throw new SQLException("[UserManageService.addUser] --> 添加失败");
            } finally {
                try {
                    DbUtils.commitAndClose(conn);
                } catch (SQLException e) {
                    throw new SQLException("[UserManageService.addUser] --> 添加失败");
                }
            }
        } else {
            throw new SQLException("[UserManageService.addUser] --> 添加失败");
        }
    }

    public void updateUser(User user, String[] roleIds, boolean isUpdatePassword) throws SQLException {
        Connection conn = DataSourceUtil.getConnection();
        if (conn != null) {
            conn.setAutoCommit(false);
            try {
                if (isUpdatePassword) {
                    dao.updateUserById(conn, user);
                } else {
                    dao.updateUserWithoutPasswordById(conn, user);
                }
                dao.deleteAllRole(conn, user);
                dao.updateUserRole(conn, user, roleIds);
            } catch (SQLException e) {
                DbUtils.rollback(conn);
                throw new SQLException("[UserManageService.updateUser] --> 更新失败");
            } finally {
                try {
                    DbUtils.commitAndClose(conn);
                } catch (SQLException e) {
                    throw new SQLException("[UserManageService.updateUser] --> 更新失败");

                }
            }
        } else {
            throw new SQLException("[UserManageService.updateUser] --> 更新失败");
        }
    }

    public User findUser(String id) throws SQLException {
        return dao.findUserById(id);

    }

    public void deleteUser(String id) throws SQLException {
        dao.deleteUserById(id);
    }
}
