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
    private Menu userName, createNewVote;
    private String user;

    @FXML
    private AnchorPane votingMenu;

    List<Card> votingCards = new ArrayList<>(); //aggregation. Menuwindow have this list but not hosting it
    @FXML
    private void initialize() {
        votingCards = Card.getCards(); //putting in aggregated list objects
        getVotings();
    }
    public void setVisibleCreating(boolean setVisibility){
        createNewVote.setVisible(setVisibility);
    }
    @FXML
    private void PassToCreationWindow(){
        SceneManager.getInstance().loadScene("CreateCardWindow.fxml");
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
           votingButton.setOnAction(event -> PassToVoteWindow(card.getId(card), votingCards));
           votingButton.setText(card.getName());
           votingMenu.getChildren().add(votingButton);
           LayoutY += 100;
       }

    }

    @FXML
    private void PassToVoteWindow(int ID, List<Card> votingCards){
        SceneManager.getInstance().loadSceneWithIdInt("VoteWindow.fxml", ID, votingCards); //setting login scene
    }
    @FXML
    private void PassToLoginWindow(){
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

}
