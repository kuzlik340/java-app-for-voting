package com.example.uvs.Citizen;
import com.example.uvs.GUI.SceneManager;


public class Administrator extends Citizen implements ActionStrategy{

    public Administrator(String login, String password) {
        super(login, password);
    }
    ActionStrategy actionStrategy = () -> {
        SceneManager.getInstance().setSetVisibility(true);
    };

    @Override
    public void performAction() {
        actionStrategy.performAction();
    }
}  //polymorphism that is used in strategy pattern to show admin button



