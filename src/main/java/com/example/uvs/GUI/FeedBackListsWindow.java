package com.example.uvs.GUI;

import com.example.uvs.Citizen.UserSession;
import com.example.uvs.FeedBacks.FeedBackForApp;
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


    @FXML
    private void passToMenuWindow(){
        SceneManager.getInstance().loadScene("MenuWindow.fxml");
    }

    @FXML
    private void PassToLoginWindow(){
        UserSession.getInstance().setStarted(false, null, null);
        LogInController.serializeSession(UserSession.getInstance());
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    @FXML
    public void initialize() {
        userName.setText(UserSession.getInstance().getLogin());
        FeedBackForApp feed = new FeedBackForApp();
        List<FeedBackForApp> feedbacks = feed.getFeedbackText();
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
            feedbackFromUser.setText(feedback.getLogin() + ": " + feedback.getFeedText());
            feedbackFromUser.setWrapText(true);
            feedbackFromUser.setStyle("-fx-background-color: #e9e3ce; -fx-padding: 10; -fx-text-fill: black");
            feedbackMenu.getChildren().add(feedbackFromUser);
            LayoutY += 100; // Increment the Y layout to avoid overlapping
        }
    }
}
