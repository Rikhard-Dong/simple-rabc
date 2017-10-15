package io.ride.test.service;

import io.ride.domain.Privilege;
import io.ride.service.PrivilegeService;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午8:38
 */
public class PrivilegeServieTest {
    PrivilegeService service = new PrivilegeService();

    @Test
    public void listAllTest() throws SQLException {
        System.out.println(service.listAll());
    }

    @Test
    public void addTest() throws SQLException {
        Privilege privilege = new Privilege();
        privilege.setPrivilege_name("测试权限");
        privilege.setPrivilege_url("/test");
        privilege.setDescription("测试权限用");
        privilege.setNo_login("1");
        service.add(privilege);
    }

    @Test
    public void findTest() throws SQLException {
        Privilege privilege = service.find("19");
        System.out.println(privilege);
    }

    @Test
    public void updateTest() throws SQLException {
        Privilege privilege = service.find("50");
        privilege.setDescription("this is test privilege");
        service.update(privilege);
    }

    @Test
    public void deleteTest() throws SQLException {
        service.delete("50");
    }
}
