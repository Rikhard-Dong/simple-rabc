package io.ride.test.service;

import io.ride.domain.User;
import io.ride.service.UserManageService;
import io.ride.util.EncryptionUtil;
import io.ride.util.RandomIdUtil;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午7:40
 */
public class UserManageServiceTest {

    private UserManageService service = new UserManageService();

    @Test
    public void listAllUseTest() throws SQLException {
        System.out.println(service.listAll());
    }

    @Test
    public void addUserTest() throws SQLException {
        User user = new User();
        user.setId(RandomIdUtil.getRandomId("userTest@gmail.com"));
        user.setEmail("userTest@gmail.com");
        user.setNickname("userTest");
        user.setPassword(EncryptionUtil.md5("123456"));
        String[] roleIds = {"22", "31"};
        service.addUser(user, roleIds);
    }

    @Test
    public void findUserTest() throws SQLException {
        User user = service.findUser("1d565a34b8cf3617818374a8fb5d30fb");
        System.out.println(user);
    }

    @Test
    public void updateUserTest() throws SQLException {
        User user = service.findUser("1d565a34b8cf3617818374a8fb5d30fb");
        user.setNickname("super_admin");
        String[] roleIds = {"21", "22", "31"};
    }

    @Test
    public void deleteUserTest() throws SQLException {
        User user = service.findUser("1d565a34b8cf3617818374a8fb5d30fb");
        service.deleteUser("1d565a34b8cf3617818374a8fb5d30fb");
    }
}
