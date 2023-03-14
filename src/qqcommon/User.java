package qqcommon;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID=1L;//‘ˆ«øºÊ»›–‘
    private String UserId;
    private String password;

    public User(String userId, String password) {
        UserId = userId;
        this.password = password;
    }

    public User() {
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
