package io.ride.domain;

import java.util.List;

/**
 * Created by IDEA
 * User: ride
 * Date: 17-10-11
 * Time: 上午10:25
 */
public class User {
    private String id;
    private String email;
    private String nickname;
    private String password;
    private String confirm;

    private List<Role> roles;

    public User() {
    }

    public User(String id, String email, String nickname, String password, String confirm, List<Role> roles) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.confirm = confirm;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", confirm='" + confirm + '\'' +
                ", roles=" + roles +
                '}';
    }
}
