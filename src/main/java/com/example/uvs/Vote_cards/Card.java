package com.example.uvs.Vote_cards;

public class Card {
    private int IdOfCard = 0;
    private int IdOfGroup = 0;
    private String name;
    private String infoOfObject;
    private String[] variants = new String[4];


    public Card(String name, String info, String variants[]){
        this.name = name;
        this.infoOfObject = info;
        for(int i = 0; i < 4; i++){
            this.variants[i] = variants[i];
        }
        this.IdOfCard += 1;
    }


    public int getId (Card card) {
        return this.IdOfCard;
    }
}
