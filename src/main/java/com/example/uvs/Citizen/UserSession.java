package com.example.uvs.Citizen;


import java.io.Serializable;

public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    private static UserSession instance; // Singleton instance
    private boolean started = false;
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

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started, String login, String password) {
        this.started = started;
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
