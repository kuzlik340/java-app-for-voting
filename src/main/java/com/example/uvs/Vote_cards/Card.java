package com.example.uvs.Vote_cards;
import com.example.uvs.DataBase.DataBaseConnection;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private int IdOfCard;
    private String name, infoOfObject, option1, option2, option3, option4;


    public Card(String name, String info, int ID, String option1, String option2, String option3, String option4){
        //setting card by this constructor
        this.name = name;
        this.infoOfObject = info;
        this.IdOfCard = ID;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
    public static List<Card> getCards() {
        //method to get all votes to display them on menu window
        return DataBaseConnection.DataBaseInterface.getCards();
    }
    public static void CreateCards(String Label, String text, String option1, String option2, String option3, String option4){
        //method to create new voting and put it in the database
        DataBaseConnection.DataBaseInterface.CreateCards(Label, text, option1, option2, option3, option4);
    }


    public int getId (Card card) {
        return card.IdOfCard;
    }
    public String getLabel (Card card) {
        return card.name;
    }
    public String getText (Card card) {
        return card.infoOfObject;
    }
    public List<String> getOptions (Card card) {
        List<String> alloptions = new ArrayList<>();
        alloptions.add(card.option1);
        alloptions.add(card.option2);
        alloptions.add(card.option3);
        alloptions.add(card.option4);
        return alloptions;
    }


    public String getName() {
        //method to return label
        return name;
    }
}
