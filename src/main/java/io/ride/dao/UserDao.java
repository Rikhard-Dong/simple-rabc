package io.ride.dao;

import io.ride.domain.User;
import io.ride.util.DataSourceUtil;
import io.ride.util.EncryptionUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午8:49
 */
public class UserDao {
    public User findUserByEmailAndPassword(User commitUser) throws SQLException {
        DataSource dataSource = DataSourceUtil.getDataSource();
        QueryRunner runner = new QueryRunner(dataSource);

        String sql = "SELECT * FROM t_user WHERE email=? AND password=?";

        User dbUser = runner.query(sql, new BeanHandler<User>(User.class), commitUser.getEmail(),
                EncryptionUtil.md5(commitUser.getPassword()));
        if (dbUser == null) {
            throw new SQLException("用户不存在");
        }

        return dbUser;
    }
}
