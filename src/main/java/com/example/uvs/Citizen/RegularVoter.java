package com.example.uvs.Citizen;

import com.example.uvs.GUI.SceneManager;

public class RegularVoter extends Citizen implements ActionStrategy{

    public RegularVoter(String id, String name) {
        super(id, name);
    }
    @Override
    public void performAction() {
        SceneManager.getInstance().setSetVisibility(false);
    }//polymorphism that is used in strategy pattern to hide admin button
}
