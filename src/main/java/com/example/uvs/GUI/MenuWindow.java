package com.example.uvs.GUI;


import com.example.uvs.Vote_cards.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;


public class MenuWindow implements PassUsername{
    @FXML
    private Button name1, name2, name3;
    @FXML
    private Menu userName;

    private String user;
    @FXML
    private AnchorPane votingMenu;

    List<Card> votingCards = new ArrayList<>();
    @FXML
    private void initialize() {
        votingCards = Card.getCards();
        getVotings();
    }

    @Override
    public void PassUser(String username) {
        this.user = username;
        userName.setText(user);
    }
    @FXML
    public void getVotings(){
       votingMenu.getChildren().clear();
       double LayoutX  = 25.0;
       double LayoutY  = 22.0;
       for (Card card: votingCards)
       {
           Button votingButton = new Button();
           votingButton.setMinHeight(77.0);
           votingButton.setMinWidth(590.0);
           votingButton.setLayoutX(LayoutX);
           votingButton.setLayoutY(LayoutY);
           votingButton.setOnAction(event -> PassToVoteWindow(card.getId(card)));
           votingButton.setText(card.getName());
           votingMenu.getChildren().add(votingButton);
           LayoutY += 100;
       }

    }

    @FXML
    private void PassToVoteWindow(int ID){
        SceneManager.getInstance().loadSceneWithIdInt("VoteWindow.fxml", ID); //setting login scene
    }
    @FXML
    private void PassToLoginWindow(){
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

}
