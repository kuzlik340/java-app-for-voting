package com.example.uvs.Citizen;

import com.example.uvs.GUI.SceneManager;
import com.example.uvs.Voting_logic.VotingProcess;

public class RegularVoter extends Citizen implements ActionStrategy{

    public RegularVoter(String id, String name) {
        super(id, name);
    }
    @Override
    public void performAction() {
        SceneManager.getInstance().setSetVisibility(false);
    }
}
