package com.example.uvs.Citizen;

import com.example.uvs.GUI.SceneManager;

/**
 * Represents an Administrator, a type of Citizen with additional administrative privileges.
 */
public class Administrator extends Citizen implements ActionStrategy {

    /**
     * Constructs an Administrator object with the provided login and password.
     *
     * @param login    the login username
     * @param password the login password
     */
    public Administrator(String login, String password) {
        super(login, password);
    }

    ActionStrategy actionStrategy = () -> {
        SceneManager.getInstance().setSetVisibility(true);
    };

    /**
     * Displays the admin features by showing the admin button.
     */
    @Override
    public void showAdminFeatures() {
        actionStrategy.showAdminFeatures();
    }
}
