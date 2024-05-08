package com.example.uvs.Citizen;

import com.example.uvs.GUI.SceneManager;

/**
 * Represents a regular voter, extending the Citizen class and implementing ActionStrategy.
 */
public class RegularVoter extends Citizen implements ActionStrategy {

    /**
     * Constructs a RegularVoter object with the provided ID and name.
     *
     * @param id   the ID of the regular voter
     * @param name the name of the regular voter
     */
    public RegularVoter(String id, String name) {
        super(id, name);
    }

    // Strategy pattern implementation
    ActionStrategy actionStrategy = () -> {
        SceneManager.getInstance().setSetVisibility(false);
    };

    /**
     * Shows admin features, using the strategy pattern to hide admin button.
     */
    @Override
    public void showAdminFeatures() {
        actionStrategy.showAdminFeatures();
    }
}
