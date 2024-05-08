package com.example.uvs.GUI;

import com.example.uvs.Citizen.UserSession;
import com.example.uvs.FeedBacks.ActionStrategy2;
import com.example.uvs.FeedBacks.FeedBackForApp;
import com.example.uvs.FeedBacks.FeedBackForVoting;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;

import java.util.List;

/**
 * The FeedBackListsWindow class controls the interface for displaying feedback lists.
 * It displays feedbacks for the application or for specific votings.
 */
public class FeedBackListsWindow {
    @FXML
    private AnchorPane feedbackMenu;
    @FXML
    private Menu userName;
    private boolean typewindow;
    ActionStrategy2 strategy;

    /**
     * Set the type of feedback window.
     *
     * @param type The type of window.
     */
    public void setType(boolean type){
        this.typewindow = type;
        List<FeedBackForApp> feedbacks;
        if(typewindow){
            strategy = new FeedBackForVoting();
            feedbacks = strategy.getFeedbackText();
        }
        else{
            strategy = new FeedBackForApp();
            feedbacks = strategy.getFeedbackText();
        }
        feedbackMenu.getChildren().clear();
        double LayoutX = 25.0;
        double LayoutY = 22.0;

        // Displaying feedbacks in the menu window from the database
        for (FeedBackForApp feedback : feedbacks) {
            Label feedbackFromUser = new Label();
            feedbackFromUser.setMinHeight(77.0);
            feedbackFromUser.setMinWidth(590.0);
            feedbackFromUser.setLayoutX(LayoutX);
            feedbackFromUser.setLayoutY(LayoutY);
            if(!typewindow){
                feedbackFromUser.setText(feedback.getLogin() + ": " + feedback.getFeedText());
            }
            else{
                feedbackFromUser.setText("User " + feedback.getLogin() + " added feedback about voting " + feedback.getNameOfVote() + "\nfeedback: " + feedback.getFeedText());
            }
            feedbackFromUser.setWrapText(true);
            feedbackFromUser.setStyle("-fx-background-color: #e9e3ce; -fx-padding: 10; -fx-text-fill: black");
            feedbackMenu.getChildren().add(feedbackFromUser);
            LayoutY += 100; // Increment the Y layout to avoid overlapping
        }
    }

    /**
     * Navigate to the menu window.
     */
    @FXML
    private void passToMenuWindow(){
        typewindow = false;
        SceneManager.getInstance().setSetVisibilityofFeed(false);
        SceneManager.getInstance().loadScene("MenuWindow.fxml");
    }

    /**
     * Navigate to the login window.
     */
    @FXML
    private void PassToLoginWindow(){
        typewindow = false;
        UserSession.getInstance().setStarted(null, null);
        SceneManager.getInstance().setSetVisibilityofFeed(false);
        LogInController.serializeSession(UserSession.getInstance());
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    /**
     * Initialize the feedback list window.
     */
    @FXML
    public void initialize() {
        userName.setText(UserSession.getInstance().getLogin());
    }
}
