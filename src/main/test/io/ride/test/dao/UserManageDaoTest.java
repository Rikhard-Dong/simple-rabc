package io.ride.test.dao;

import io.ride.dao.UserManageDao;
import io.ride.domain.User;
import io.ride.util.DataSourceUtil;
import io.ride.util.EncryptionUtil;
import io.ride.util.RandomIdUtil;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午5:49
 */
public class UserManageDaoTest {

    private UserManageDao dao = new UserManageDao();

    @Test
    public void findAllUserTest() throws SQLException {
        System.out.println((new UserManageDao()).findAllUser());
    }

    @Test
    public void addUserTest() throws SQLException {
        User user = new User();
        user.setId(RandomIdUtil.getRandomId("userTest@gmail.com"));
        user.setEmail("userTest@gmail.com");
        user.setNickname("userTest");
        user.setPassword(EncryptionUtil.md5("123456"));
        dao.addUser(DataSourceUtil.getConnection(), user);
    }

    @Test
    public void findUserByIdTest() throws SQLException {
        User user = dao.findUserById("1d565a34b8cf3617818374a8fb5d30fb");
        System.out.println(user);
    }

    @Test
    public void updateUserByIdTest() throws SQLException {
        User user = dao.findUserById("1d565a34b8cf3617818374a8fb5d30fb");
        user.setNickname("user_test");
        dao.updateUserById(DataSourceUtil.getConnection(), user);
    }

    @Test
    public void updateUserRoleTest() throws SQLException {
        User user = dao.findUserById("1d565a34b8cf3617818374a8fb5d30fb");
        String[] roleIds = {"22", "31"};
        dao.updateUserRole(DataSourceUtil.getConnection(), user, roleIds);
    }

    @Test
    public void deleteAllRoleTest() throws SQLException {
        User user = dao.findUserById("1d565a34b8cf3617818374a8fb5d30fb");
        dao.deleteAllRole(DataSourceUtil.getConnection(), user);
    }

    @Test
    public void deleteUserByIdTest() throws SQLException {
        User user = dao.findUserById("1d565a34b8cf3617818374a8fb5d30fb");
        dao.deleteUserById(user.getId());
    }
}
