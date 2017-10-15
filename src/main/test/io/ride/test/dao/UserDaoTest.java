package io.ride.test.dao;

import io.ride.dao.UserDao;
import io.ride.domain.User;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午9:10
 */
public class UserDaoTest {
    @Test
    public void findUserByEmailAndPasswordTest() throws SQLException {
        User commitUser = new User();
        commitUser.setEmail("admin@gmail.com");
        commitUser.setPassword("ABCabc123#");
        UserDao userDao = new UserDao();
        User dbUser = userDao.findUserByEmailAndPassword(commitUser);
        System.out.println(dbUser);
    }
}
