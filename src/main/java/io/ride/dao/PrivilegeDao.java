package io.ride.dao;

import io.ride.domain.Privilege;
import io.ride.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-12
 * Time: 下午8:01
 */
public class PrivilegeDao {
    private QueryRunner runner = new QueryRunner(DataSourceUtil.getDataSource());

    /**
     * 查询所有权限
     *
     * @return 所有权限
     * @throws SQLException 查询异常
     */
    public List<Privilege> findAllPrivilege() throws SQLException {
        String sql = "SELECT * FROM t_privilege;";
        List<Privilege> privileges = runner.query(sql, new BeanListHandler<Privilege>(Privilege.class));
        if (privileges == null) {
            throw new SQLException("[PrivilegeDao.findAllPrivilege] --> 未查询到结果");
        }
        return privileges;
    }

    /**
     * 查找所有需要登录的权限
     *
     * @return 所有需要登录访问的权限
     * @throws SQLException 查询异常
     */
    public List<Privilege> findAllLoginPrivilege() throws SQLException {
        String sql = "SELECT * FROM t_privilege WHERE no_login='0';";
        List<Privilege> privileges = runner.query(sql, new BeanListHandler<Privilege>(Privilege.class));
        if (privileges == null) {
            throw new SQLException("[PrivilegeDao.findAllPrivilege] --> 未查询到结果");
        }
        return privileges;
    }

    /**
     * 添加权限
     *
     * @param privilege 待添加的权限
     * @throws SQLException 添加异常
     */
    public void addPrivilege(Privilege privilege) throws SQLException {
        String sql = "INSERT INTO t_privilege(privilege_name, privilege_url, description, no_login) " +
                "VALUE(?,?,?,?)";
        int result = runner.update(sql, privilege.getPrivilege_name(), privilege.getPrivilege_url(),
                privilege.getDescription(), privilege.getNo_login());
        if (result <= 0) {
            throw new SQLException("[PrivilegeDao.addPrivilege] --> 添加权限失败");
        }
    }


    /**
     * 根据权限ID查询权限
     *
     * @param id 待查询权限ID
     * @return 查询到的权限
     * @throws SQLException 查询异常
     */
    public Privilege findPrivilegeById(String id) throws SQLException {
        String sql = "SELECT * FROM t_privilege WHERE id=?;";
        Privilege privilege = runner.query(sql, new BeanHandler<Privilege>(Privilege.class), id);
        if (privilege == null) {
            throw new SQLException("[PrivilegeDao.findPrivilegeById] --> 查询权限异常");
        }
        return privilege;
    }

    /**
     * 根据权限ID更新权限
     *
     * @param privilege 待更新权限
     * @throws SQLException 更新异常
     */
    public void updatePrivilegeById(Privilege privilege) throws SQLException {
        String sql = "UPDATE t_privilege SET privilege_name=?, privilege_url=?, " +
                "description=?, no_login=? WHERE id=?";
        int result = runner.update(sql, privilege.getPrivilege_name(), privilege.getPrivilege_url(),
                privilege.getDescription(), privilege.getNo_login(), privilege.getId());
        if (result <= 0) {
            throw new SQLException("[PrivilegeDao.updatePrivilegeById] --> 更新权限异常");
        }
    }

    /**
     * 根据权限ID删除权限
     *
     * @param id 待删除权限ID
     * @throws SQLException 删除异常
     */
    public void deletePrivilegeById(String id) throws SQLException {
        String sql = "Delete FROM t_privilege WHERE id = ?;";
        int result = runner.update(sql, id);
        if (result <= 0) {
            throw new SQLException("[PrivilegeDao.deletePrivilegeById] --> 删除权限异常");
        }
    }
}
