package io.ride.domain;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 上午10:27
 */
public class Privilege {
    private Integer id;
    private String privilege_name;
    private String privilege_url;
    private String description;
    private String no_login;

    public Privilege() {
    }

    public Privilege(Integer id, String privilege_name, String privilege_url, String description, String no_login) {
        this.id = id;
        this.privilege_name = privilege_name;
        this.privilege_url = privilege_url;
        this.description = description;
        this.no_login = no_login;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrivilege_name() {
        return privilege_name;
    }

    public void setPrivilege_name(String privilege_name) {
        this.privilege_name = privilege_name;
    }

    public String getPrivilege_url() {
        return privilege_url;
    }

    public void setPrivilege_url(String privilege_url) {
        this.privilege_url = privilege_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNo_login() {
        return no_login;
    }

    public void setNo_login(String no_login) {
        this.no_login = no_login;
    }

    @Override
    public String toString() {
        return "Privilege{" +
                "id=" + id +
                ", privilege_name='" + privilege_name + '\'' +
                ", privilege_url='" + privilege_url + '\'' +
                ", description='" + description + '\'' +
                ", no_login='" + no_login + '\'' +
                '}';
    }
}
