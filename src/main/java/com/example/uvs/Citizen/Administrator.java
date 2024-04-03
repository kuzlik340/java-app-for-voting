package com.example.uvs.Citizen;
import com.example.uvs.GUI.SceneManager;
import com.example.uvs.Vote_cards.Card;


public class Administrator extends Citizen implements ActionStrategy{
    private String Label, text, option1, option2, option3, option4;

    public Administrator(String login, String password) {
        super(login, password);
    }


    @Override
    public void performAction() {
        SceneManager.getInstance().setSetVisibility(true);
    }
}



