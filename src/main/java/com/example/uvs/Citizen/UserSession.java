package com.example.uvs.Citizen;

import java.io.Serializable;

/**
 * Represents a user session with login information.
 */
public class UserSession implements Serializable {
    private static final long serialVersionUID = 1L;
    private static UserSession instance;
    private String login;
    private String password;

    /**
     * Private constructor to prevent instantiation from outside the class.
     */
    private UserSession() {
    }

    /**
     * Gets the singleton instance of UserSession.
     *
     * @return the UserSession instance
     */
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

    /**
     * Sets the login information for the user session.
     *
     * @param login    the login username
     * @param password the login password
     */
    public void setStarted(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Gets the login username of the user session.
     *
     * @return the login username
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets the login password of the user session.
     *
     * @return the login password
     */
    public String getPassword() {
        return password;
    }
}
