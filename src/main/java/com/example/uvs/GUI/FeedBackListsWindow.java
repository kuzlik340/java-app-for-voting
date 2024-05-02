package com.example.uvs.GUI;

import com.example.uvs.Citizen.Administrator;
import com.example.uvs.Citizen.UserSession;
import com.example.uvs.FeedBacks.ActionStrategy2;
import com.example.uvs.FeedBacks.FeedBackForApp;
import com.example.uvs.FeedBacks.FeedBackForVoting;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class FeedBackListsWindow {
    @FXML
    private AnchorPane feedbackMenu;
    @FXML
    private Menu userName;
    private boolean typewindow;
    ActionStrategy2 strategy;
    public void setType(boolean type){
        this.typewindow = type;
        List<FeedBackForApp> feedbacks;
        System.out.println("From feeedbacklistswindow"+type);
        if(typewindow){
            System.out.println("We reached!!!!");
            strategy = new FeedBackForVoting();
            feedbacks = strategy.getFeedbackText();
            System.out.println("type = " + typewindow);
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
    @FXML
    private void passToMenuWindow(){
        typewindow = false;
        SceneManager.getInstance().setSetVisibilityofFeed(false);
        SceneManager.getInstance().loadScene("MenuWindow.fxml");
    }

    @FXML
    private void PassToLoginWindow(){
        typewindow = false;
        UserSession.getInstance().setStarted(false, null, null);
        SceneManager.getInstance().setSetVisibilityofFeed(false);
        LogInController.serializeSession(UserSession.getInstance());
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    @FXML
    public void initialize() {
        userName.setText(UserSession.getInstance().getLogin());
    }
}
