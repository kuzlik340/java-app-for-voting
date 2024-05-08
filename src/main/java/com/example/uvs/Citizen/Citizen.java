package com.example.uvs.Citizen;

import com.example.uvs.DataBase.DataBaseConnection;
import com.example.uvs.DataBase.PasswordInCorrectException;

/**
 * Represents an abstract class for citizens, including voters and administrators.
 */
public abstract class Citizen {
    protected int id;
    protected String name;
    protected String login;
    protected String password;

    /**
     * Constructs a Citizen object with the provided login and password.
     *
     * @param login    the login username
     * @param password the login password
     */
    public Citizen(String login, String password) {
        this.login = login;
        this.password = password;
    }

    /**
     * Checks if the login information is valid.
     *
     * @param login    the login username
     * @param password the login password
     * @return true if the login information is valid, false otherwise
     */
    public static boolean checkLogInfo(String login, String password) {
        return DataBaseConnection.DataBaseInterface.checkLogInfo(login, password);
    }

    /**
     * Checks if the user is an administrator.
     *
     * @param login    the login username
     * @param password the login password
     * @return the ID of the admin if the user is an admin, otherwise returns -1
     */
    public static int checkAdmin(String login, String password) {
        return DataBaseConnection.DataBaseInterface.checkAdmin(login, password);
    }

    /**
     * Checks if the password is correct for the given login.
     *
     * @param login    the login username
     * @param password the login password
     * @return true if the password is correct, otherwise throws PasswordInCorrectException
     * @throws PasswordInCorrectException if the password is incorrect
     */
    public static boolean checkPassword(String login, String password) throws PasswordInCorrectException {
        return DataBaseConnection.DataBaseInterface.checkPassword(login, password);
    }

    /**
     * Gets the login username.
     *
     * @return the login username
     */
    public String getLogin() {
        return login;
    }

    /**
     * Adds a new user to the system.
     *
     * @param login    the login username
     * @param password the login password
     */
    public void addUser(String login, String password) {
        DataBaseConnection.DataBaseInterface.addUser(login, password);
    }

    /**
     * Abstract method to show admin features.
     */
    public abstract void showAdminFeatures();
}
