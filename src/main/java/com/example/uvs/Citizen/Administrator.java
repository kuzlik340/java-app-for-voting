package com.example.uvs.Citizen;
import com.example.uvs.GUI.SceneManager;


public class Administrator extends Citizen implements ActionStrategy{

    public Administrator(String login, String password) {
        super(login, password);
    }


    @Override
    public void performAction() {
        SceneManager.getInstance().setSetVisibility(true);
    }
}  //polymorphism that is used in strategy pattern to show admin button



