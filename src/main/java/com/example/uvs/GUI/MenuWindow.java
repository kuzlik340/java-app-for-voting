package com.example.uvs.GUI;


import com.example.uvs.Citizen.UserSession;
import com.example.uvs.Vote_cards.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;


public class MenuWindow implements PassUsername{
    @FXML
    private Menu userName, adminfeatures;
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
        adminfeatures.setVisible(setVisibility);
    }
    @FXML
    private void PassToCreationWindow(){
        SceneManager.getInstance().loadScene("CreateCardWindow.fxml");
        //if admin is signed he can pass to window where he can create new voting
    }
    @FXML
    public void PassToAllFeedBacksWindow(){
        SceneManager.getInstance().feedbacktype(false);
        SceneManager.getInstance().loadScene("FeedBackListWindow.fxml");
    }

    @Override
    public void PassUser(String username) {
        //pass username to show it in left top side of window
        this.user = username;
        userName.setText(user);
    }
    @FXML
    public void PassToAllFeedBacksVotingWindow(){
        SceneManager.getInstance().feedbacktype(true);
        SceneManager.getInstance().loadScene("FeedBackListWindow.fxml");
    }
    @FXML
    public void getVotings(){
       votingMenu.getChildren().clear();
       double LayoutX  = 25.0;
       double LayoutY  = 22.0;
       //setting all votings in menu window from database
       for (Card card: votingCards)
       {
           Button votingButton = new Button();
           votingButton.setMinHeight(77.0);
           votingButton.setMinWidth(590.0);
           votingButton.setLayoutX(LayoutX);
           votingButton.setLayoutY(LayoutY);
           //if button was clicked
           votingButton.setOnAction(event -> PassToVoteWindow(card.getId(card), votingCards));
           votingButton.setText(card.getName());
           votingMenu.getChildren().add(votingButton);
           LayoutY += 100;
       }

    }
    @FXML
    private void PassToFeedBackWindow(){
        SceneManager.getInstance().loadScene("FeedBackWindow.fxml"); //setting voting scene
    }

    @FXML
    private void PassToVoteWindow(int ID, List<Card> votingCards){
        SceneManager.getInstance().loadSceneWithIdInt("VoteWindow.fxml", ID, votingCards); //setting voting scene
    }
    @FXML
    private void PassToLoginWindow(){
        UserSession.getInstance().setStarted(false, null, null);
        LogInController.serializeSession(UserSession.getInstance());
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

}
