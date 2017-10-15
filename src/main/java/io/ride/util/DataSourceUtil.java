package io.ride.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午12:39
 */
public class DataSourceUtil {
    private static Connection conn;
    private static ComboPooledDataSource cpds = new ComboPooledDataSource();

    public static Connection getConnection() {
        /*try {
            cpds.setDriverClass("com.mysql.jdbc.Driver");
            cpds.setJdbcUrl("jdbc:mysql://localhost:3306/db_rbac?characterEncoding=UTF8");
            cpds.setUser("root");
            cpds.setPassword("ABCabc123#");
            conn = cpds.getConnection();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        try {
            return cpds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        /*try {
            conn = cpds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;*/
    }

    public static DataSource getDataSource() {
        return cpds;
    }
}
