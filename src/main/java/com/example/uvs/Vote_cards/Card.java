/**
 * The Card class represents a voting card with its associated attributes such as ID, name, information,
 * and options.
 */
package com.example.uvs.Vote_cards;
import com.example.uvs.DataBase.DataBaseConnection;

import java.util.ArrayList;
import java.util.List;

public class Card {
    private int IdOfCard;
    private String name, infoOfObject, option1, option2, option3, option4;

    /**
     * Parameterized constructor for the Card class.
     *
     * @param name      The name of the voting card.
     * @param info      The information associated about the voting object.
     * @param ID        The ID of the voting card.
     * @param option1   The first option of the voting card.
     * @param option2   The second option of the voting card.
     * @param option3   The third option of the voting card.
     * @param option4   The fourth option of the voting card.
     */
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
    /**
     * Method to retrieve all voting cards from the database.
     *
     * @return A list of voting cards.
     */
    public static List<Card> getCards() {
        //method to get all votes to display them on menu window
        return DataBaseConnection.DataBaseInterface.getCards();
    }
    /**
     * Method to create a new voting card and store it in the database.
     *
     * @param name      The name for the new voting card.
     * @param info      The information associated about the voting object.
     * @param option1   The first option for the new voting card.
     * @param option2   The second option for the new voting card.
     * @param option3   The third option for the new voting card.
     * @param option4   The fourth option for the new voting card.
     */
    public static void CreateCards(String name, String info, String option1, String option2, String option3, String option4){
        //method to create new voting and put it in the database
        DataBaseConnection.DataBaseInterface.CreateCards(name, info, option1, option2, option3, option4);
    }

    /**
     * Method to retrieve the ID of a voting card.
     *
     * @param card The voting card object.
     * @return The ID of the voting card.
     */
    public int getId (Card card) {
        return card.IdOfCard;
    }
    /**
     * Method to retrieve the label of a voting card.
     *
     * @param card The voting card object.
     * @return The label of the voting card.
     */
    public String getName (Card card) {
        return card.name;
    }
    /**
     * Method to retrieve the text or information associated with a voting card.
     *
     * @param card The voting card object.
     * @return The text or information of the voting card.
     */
    public String getText (Card card) {
        return card.infoOfObject;
    }
    /**
     * Method to retrieve the list of options associated with a voting card.
     *
     * @param card The voting card object.
     * @return A list of options for the voting card.
     */
    public List<String> getOptions (Card card) {
        List<String> alloptions = new ArrayList<>();
        alloptions.add(card.option1);
        alloptions.add(card.option2);
        alloptions.add(card.option3);
        alloptions.add(card.option4);
        return alloptions;
    }
}
