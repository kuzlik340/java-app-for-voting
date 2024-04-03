package com.example.uvs.Citizen;
import com.example.uvs.Vote_cards.Card;


public class Administrator extends Citizen {
    private String Label, text, option1, option2, option3, option4;

    public Administrator(String login, String password) {
        super(login, password);
    }

    public void setAll(String label, String text, String option1, String option2, String option3, String option4) {
        this.Label = label;
        this.text = text;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }

    @Override
    public void performAction() {
        Card.CreateCards(Label, text, option1, option2, option3, option4);
    }
}



