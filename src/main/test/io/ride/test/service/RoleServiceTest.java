package io.ride.test.service;

import io.ride.domain.Role;
import io.ride.service.RoleService;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午9:18
 */
public class RoleServiceTest {
    private RoleService service = new RoleService();

    @Test
    public void listAllTest() throws SQLException {
        System.out.println(service.listAll());
    }

    @Test
    public void findTest() throws SQLException {
        System.out.println(service.find("21"));
    }

    @Test
    public void addTest() throws SQLException {
        Role role = new Role();
        role.setRole_name("测试角色");
        role.setDescription("这是一个测试角色");
        String[] privilegeIds = {"19", "21", "22", "24"};
        service.add(role, privilegeIds);
    }

    @Test
    public void updateTest() throws SQLException {
        Role role = service.find("55");
        String[] privilegeIds = {"19", "21", "22", "24", "31"};
        service.update(role, privilegeIds);
    }

    @Test
    public void deleteTest() throws SQLException {
        service.delete("53");
    }
}
