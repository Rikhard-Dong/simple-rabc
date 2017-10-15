package io.ride.domain;

import java.util.List;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 上午10:27
 */
public class Role {
    private Integer id;
    private String role_name;
    private String description;

    private List<Privilege> privileges;

    public Role() {
    }

    public Role(Integer id, String role_name, String description, List<Privilege> privileges) {
        this.id = id;
        this.role_name = role_name;
        this.description = description;
        this.privileges = privileges;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                ", description='" + description + '\'' +
                ", privileges=" + privileges +
                '}';
    }
}
