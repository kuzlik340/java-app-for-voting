package com.example.uvs.Citizen;

import com.example.uvs.GUI.SceneManager;

/**
 * Represents regular voter, a regular type of Citizen.
 * Class extending the {@link Citizen} class and implementing {@link ActionStrategy}.
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


    /**
     * Shows admin features, using the strategy pattern to hide admin button.
     */
    @Override
    public void showAdminFeatures() {
        SceneManager.getInstance().setSetVisibility(false);
    }
}
