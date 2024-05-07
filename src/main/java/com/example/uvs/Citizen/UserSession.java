package com.example.uvs.Citizen;


import java.io.Serializable;

public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    private static UserSession instance;
    private String login;
    private String password;

    private UserSession() {
        // Private constructor to prevent instantiation from outside the class
    }

    public static UserSession getInstance() {
        if (instance == null) {
            synchronized (UserSession.class) {
                if (instance == null) {
                    instance = new UserSession();
                }
            }
        }
        return instance;
    }
    public void setStarted(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
