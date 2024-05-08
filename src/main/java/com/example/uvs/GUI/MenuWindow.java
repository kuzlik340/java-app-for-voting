package com.example.uvs.GUI;

import com.example.uvs.Citizen.UserSession;
import com.example.uvs.Vote_cards.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;


import java.util.ArrayList;
import java.util.List;

/**
 * The MenuWindow class controls the main menu interface.
 * It displays the user's username, provides options for navigating to different screens,
 * and shows available voting cards.
 * also on this screen users can put their feedback, and admin can create nev voting and check all feedbacks.
 */

public class MenuWindow {
    @FXML
    private Menu userName, adminfeatures;
    private String user;

    @FXML
    private AnchorPane votingMenu;

    List<Card> votingCards = new ArrayList<>(); //aggregation. Menuwindow have this list but not hosting it

    /**
     * Only after initializing we are putting all votings on screen.
     */
    @FXML
    private void initialize() {


        userName.setText(UserSession.getInstance().getLogin());
        this.user = UserSession.getInstance().getLogin();
        votingCards = Card.getCards(); //putting in aggregated list objects
        getVotings();
    }

    /**
     * Set visibility for admin features.
     *
     * @param setVisibility The visibility flag.
     */
    public void setVisibleCreating(boolean setVisibility){
        adminfeatures.setVisible(setVisibility);
    }

    /**
     * Navigate to the create voting card window.(works only for admin user)
     */
    @FXML
    private void PassToCreationWindow(){
        SceneManager.getInstance().loadScene("CreateCardWindow.fxml");
        //if admin is signed he can pass to window where he can create new voting
    }

    /**
     * Navigate to the feedback list window for all app feedbacks.
     */
    @FXML
    public void PassToAllFeedBacksWindow(){
        //method to show all feedbacks on App
        //here we are setting type of feedback lists (false - feedback for app, true- feeedback for votings)
        SceneManager.getInstance().feedbacktype(false);
        SceneManager.getInstance().loadScene("FeedBackListWindow.fxml");
    }

    /**
     * Pass the username to the menu window, so it will be displayed on the top left.
     *
     * @param username The username.
     */

    /**
     * Navigate to the feedback list window for voting feedbacks.
     */
    @FXML
    public void PassToAllFeedBacksVotingWindow(){
        //here we are setting type of feedback lists (false - feedback for app, true- feeedback for votings)
        SceneManager.getInstance().feedbacktype(true);
        SceneManager.getInstance().loadScene("FeedBackListWindow.fxml");
    }

    /**
     * Get available votings and display them in the menu window.
     */
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
            votingButton.setText(card.getName(card));
            votingMenu.getChildren().add(votingButton);
            LayoutY += 100;
        }
    }

    /**
     * Navigate to the feedback window. In this window users can put their opinion about the app
     */
    @FXML
    private void PassToFeedBackWindow(){
        SceneManager.getInstance().loadScene("FeedBackWindow.fxml"); //setting voting scene
    }

    /**
     * Navigate to the voting window.
     *
     * @param ID The ID of the voting card.
     * @param votingCards The list of voting cards.
     */
    @FXML
    private void PassToVoteWindow(int ID, List<Card> votingCards){
        SceneManager.getInstance().loadSceneWithIdInt("VoteWindow.fxml", ID, votingCards); //setting voting scene
    }

    /**
     * Navigate to the login window. If user pressed the 'log out' button
     */
    @FXML
    private void PassToLoginWindow(){
        UserSession.getInstance().setStarted( null, null);
        LogInController.serializeSession(UserSession.getInstance());
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }
}
