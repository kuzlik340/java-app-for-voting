package com.example.uvs.Citizen;

import com.example.uvs.FeedBacks.ActionStrategy2;
import com.example.uvs.FeedBacks.FeedBackForApp;
import com.example.uvs.GUI.SceneManager;

/**
 * Represents an Administrator, a type of Citizen with additional administrative privileges.
 * Class extending the {@link Citizen} class and implementing {@link ActionStrategy}.
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

    /**
     * Shows admin features, using the strategy pattern to show admin button.
     */
    @Override
    public void showAdminFeatures() {
        SceneManager.getInstance().setSetVisibility(true);
    }
}
