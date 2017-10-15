package io.ride.dao;

import io.ride.domain.Role;
import io.ride.domain.User;
import io.ride.util.DataSourceUtil;
import io.ride.util.EncryptionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午5:40
 */
public class UserManageDao {

    /**
     * 得到所有的用户
     *
     * @return 用户集合
     * @throws SQLException 未查询到结果
     */
    public List<User> findAllUser() throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());
        String sql = "SELECT * FROM t_user";

        List<User> users = runner.query(sql, new BeanListHandler<User>(User.class));

        if (users == null) {
            throw new SQLException("[UserManageDao.findAllUser] --> 未查询到结果!");
        }
        sql = "SELECT r.* FROM t_role AS r, t_user AS u, t_user_role AS ur" +
                " WHERE r.id = ur.role_id AND u.id = ur.user_id AND u.id = ?;";
        for (User user : users) {
            List<Role> roles = runner.query(sql, new BeanListHandler<Role>(Role.class), user.getId());
            user.setRoles(roles);
        }

        return users;
    }

    /**
     * 添加用户
     *
     * @param conn 数据库链接
     * @param user 待添加用户
     * @throws SQLException 操作失败
     */
    public void addUser(Connection conn, User user) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "INSERT INTO t_user(id, email, nickname, password) VALUE(?,?,?,?)";
        int result = runner.update(conn, sql, user.getId(), user.getEmail(), user.getNickname(),
                EncryptionUtil.md5(user.getPassword()));
        if (result <= 0) {
            throw new SQLException("[UserManagerDao.addUser] --> 添加用户失败");
        }
    }

    /**
     * 根据用户ID查询用户
     *
     * @param userId 待查询用户ID
     * @return 查询到的用户
     * @throws SQLException 查询用户失败
     */
    public User findUserById(String userId) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());

        String sql = "SELECT * FROM t_user WHERE id = ?";
        User user = runner.query(sql, new BeanHandler<User>(User.class), userId);

        if (user == null) {
            throw new SQLException("[UserManageDao.finUserById] --> 查询用户失败");
        }
        sql = "SELECT r.* FROM t_role AS r, t_user AS u, t_user_role AS ur" +
                " WHERE r.id = ur.role_id AND u.id = ur.user_id AND u.id = ?;";
        List<Role> roles = runner.query(sql, new BeanListHandler<Role>(Role.class), user.getId());
        user.setRoles(roles);

        return user;
    }

    /**
     * 根据用户ID删除用户
     *
     * @param id 用户ID
     * @throws SQLException 删除用户失败
     */
    public void deleteUserById(String id) throws SQLException {
        QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());

        String sql = "DELETE FROM t_user WHERE id = ?";
        int result = runner.update(sql, id);
        if (result <= 0) {
            throw new SQLException("[UserManageDao.deleteById] --> 用户删除操作失败");
        }
    }

    /**
     * 根据用户id更新用户
     *
     * @param conn 数据库链接
     * @param user 待更新用户
     * @throws SQLException 用户更新异常
     */
    public void updateUserById(Connection conn, User user) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "UPDATE t_user SET email=?, nickname=?, password=? WHERE id=?";
        int result = runner.update(conn, sql, user.getEmail(), user.getNickname(),
                EncryptionUtil.md5(user.getPassword()), user.getId());
        if (result <= 0) {
            throw new SQLException("[UserManageDao.updateUserById] --> 更新用户失败");
        }
    }

    public void updateUserWithoutPasswordById(Connection conn, User user) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "UPDATE t_user SET email=?, nickname=? WHERE id=?";
        int result = runner.update(conn, sql, user.getEmail(), user.getNickname(), user.getId());
        if (result <= 0) {
            throw new SQLException("[UserManageDao.updateUserById] --> 更新用户失败");
        }
    }

    /**
     * 更新用户角色
     *
     * @param conn    数据库链接
     * @param user    待更新用户
     * @param roleIds 待更新用户角色
     * @throws SQLException 删除角色失败
     */
    public void updateUserRole(Connection conn, User user, String[] roleIds) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "INSERT INTO t_user_role(user_id, role_id) VALUES (?,?);";
        Object[][] params = new Object[roleIds.length][2];
        for (int i = 0; i < roleIds.length; i++) {
            params[i][0] = user.getId();
            params[i][1] = roleIds[i];
        }
        int[] results = runner.batch(conn, sql, params);
        for (int result : results) {
            if (result <= 0) {
                throw new SQLException("[UserManagerDao.updateUserRole] --> 更新用户权限失败");
            }
        }
    }

    /**
     * 删除用户所有角色
     *
     * @param conn 数据库链接
     * @param user 待删除角色用户
     * @throws SQLException 删除用户角色失败
     */
    public void deleteAllRole(Connection conn, User user) throws SQLException {
        QueryRunner runner = new QueryRunner();
        String sql = "DELETE FROM t_user_role WHERE user_id = ?;";
        int result = runner.update(conn, sql, user.getId());
        if (result <= 0) {
            throw new SQLException("[UserManageDao.deleteAllRole] --> 删除用户权限失败");
        }
    }
}
