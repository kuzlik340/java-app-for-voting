package com.example.uvs.Vote_cards;
import com.example.uvs.DataBase.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Card {
    private int IdOfCard;
    private String name, infoOfObject, option1, option2, option3, option4;


    public Card(String name, String info, int ID, String option1, String option2, String option3, String option4){
        this.name = name;
        this.infoOfObject = info;
        this.IdOfCard = ID;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
    public static List<Card> getCards() {
        return DataBaseConnection.DataBaseInterface.getCards();
    }
public static void CreateCards(String Label, String text, String option1, String option2, String option3, String option4){
    DataBaseConnection.DataBaseInterface.CreateCards(Label, text, option1, option2, option3, option4);
}


    public int getId (Card card) {
        return this.IdOfCard;
    }
    public String getLabel (Card card) {
        return this.name;
    }
    public String getText (Card card) {
        return this.infoOfObject;
    }
    public List<String> getOptions (Card card) {
        List<String> alloptions = new ArrayList<>();
        alloptions.add(option1);
        alloptions.add(option2);
        alloptions.add(option3);
        alloptions.add(option4);
        return alloptions;
    }


    public String getName() {
        return name;
    }
}
