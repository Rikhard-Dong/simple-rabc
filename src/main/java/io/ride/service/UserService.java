package io.ride.service;

import io.ride.dao.UserDao;
import io.ride.domain.User;

import java.sql.SQLException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午9:53
 */
public class UserService {
    public User login(User commitUser) throws SQLException {
        UserDao userDao = new UserDao();

        User dbUser = userDao.findUserByEmailAndPassword(commitUser);

        return dbUser;
    }
}
