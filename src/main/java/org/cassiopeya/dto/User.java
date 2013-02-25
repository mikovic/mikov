package org.cassiopeya.dto;

/**
 * Created with IntelliJ IDEA.
 * User: Masha
 * Date: 13.02.13
 * Time: 9:24
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private int userId;
    private String userLogin;
    private String password;
    private String email;

    public User(int userId, String userLogin, String password, String email) {
        this.userId = userId;
        this.userLogin = userLogin;
        this.password = password;
        this.email = email;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
