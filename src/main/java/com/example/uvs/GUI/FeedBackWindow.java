package com.example.uvs.GUI;

import com.example.uvs.Citizen.UserSession;
import com.example.uvs.FeedBacks.FeedBackForApp;
import com.example.uvs.FeedBacks.FeedBackForVoting;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;

public class FeedBackWindow {
    @FXML
    TextArea feedbacktext, votingname;
    @FXML
    private Menu userName;
    boolean visible;
    @FXML
    public void initialize(){
        userName.setText(UserSession.getInstance().getLogin());
        System.out.println("initializing");
    }
    @FXML
    private void PassToLoginWindow(){
        votingname.setVisible(false);
        SceneManager.getInstance().setSetVisibilityofFeed(false);
        UserSession.getInstance().setStarted(null, null);
        LogInController.serializeSession(UserSession.getInstance());
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }
    @FXML
    private void passToMenuWindow(){
        votingname.setVisible(false);
        SceneManager.getInstance().setSetVisibilityofFeed(false);
        SceneManager.getInstance().loadScene("MenuWindow.fxml");
    }
    @FXML
    private void addNewFeedback(){
        String votename = votingname.getText();
        String text = feedbacktext.getText();
        String username = UserSession.getInstance().getLogin();
        System.out.println(username);
        if(!votename.isEmpty()){
            FeedBackForVoting feedback = new FeedBackForVoting();
            feedback.addFeedBack(votename, text, username);
            feedbacktext.clear();
            votingname.clear();
        }
        else{
            FeedBackForApp feedback = new FeedBackForApp();
            feedback.addFeedBack(text, username);
            feedbacktext.clear();
        }
    }
    public void setVisibleTextField(boolean visible){
        votingname.setVisible(visible);
        System.out.println(visible);
    }



}
