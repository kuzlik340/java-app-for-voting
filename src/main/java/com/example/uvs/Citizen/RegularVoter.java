package com.example.uvs.Citizen;

import com.example.uvs.GUI.SceneManager;

public class RegularVoter extends Citizen implements ActionStrategy{

    public RegularVoter(String id, String name) {
        super(id, name);
    }
    ActionStrategy actionStrategy = () -> {
        SceneManager.getInstance().setSetVisibility(false);
    };
    @Override
    public void showAdminFeatures() {
        actionStrategy.showAdminFeatures();
    }//polymorphism that is used in strategy pattern to hide admin button
}
