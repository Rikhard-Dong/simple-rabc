package io.ride.service;

import io.ride.dao.PrivilegeDao;
import io.ride.domain.Privilege;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午8:26
 */
public class PrivilegeService {
    private PrivilegeDao dao = new PrivilegeDao();

    public List<Privilege> listAll() throws SQLException {
        return dao.findAllPrivilege();
    }

    public List<Privilege> listAllLogin() throws SQLException {
        return dao.findAllLoginPrivilege();
    }

    public void add(Privilege privilege) throws SQLException {
        dao.addPrivilege(privilege);
    }

    public Privilege find(String id) throws SQLException {
        return dao.findPrivilegeById(id);
    }

    public void update(Privilege privilege) throws SQLException {
        dao.updatePrivilegeById(privilege);
    }

    public void delete(String id) throws SQLException {
        dao.deletePrivilegeById(id);
    }
}
