package com.example.uvs.GUI;

import com.example.uvs.Citizen.UserSession;
import com.example.uvs.FeedBacks.FeedBackForApp;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;

public class FeedBackWindow {
    @FXML
    TextArea feedbacktext;
    @FXML
    private Menu userName;
    @FXML
    public void initialize(){
        userName.setText(UserSession.getInstance().getLogin());
    }
    @FXML
    private void PassToLoginWindow(){
        UserSession.getInstance().setStarted(false, null, null);
        LogInController.serializeSession(UserSession.getInstance());
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }
    @FXML
    private void passToMenuWindow(){
        SceneManager.getInstance().loadScene("MenuWindow.fxml");
    }
    @FXML
    private void addNewFeedback(){
        String text = feedbacktext.getText();
        String id = UserSession.getInstance().getLogin();
        System.out.println(id);
        FeedBackForApp feedback = new FeedBackForApp();
        feedback.addFeedBack(text, id);
        feedbacktext.clear();
    }



}
