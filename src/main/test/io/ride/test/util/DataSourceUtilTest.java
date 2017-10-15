package io.ride.test.util;

import io.ride.util.DataSourceUtil;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 下午12:41
 */
public class DataSourceUtilTest {

    @Test
    public void getConnectionTest() {
        System.out.println(DataSourceUtil.getConnection());
    }

    @Test
    public void getDataSourceTest() {
        assertNotNull(DataSourceUtil.getDataSource());
        System.out.println(DataSourceUtil.getDataSource());
    }
}
