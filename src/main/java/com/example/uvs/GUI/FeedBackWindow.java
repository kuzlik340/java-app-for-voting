package com.example.uvs.GUI;

import com.example.uvs.Citizen.UserSession;
import com.example.uvs.FeedBacks.FeedBackForApp;
import com.example.uvs.FeedBacks.FeedBackForVoting;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;

/**
 * The FeedBackWindow class controls the feedback window interface.
 * It allows users to submit feedback for the application or for specific votings.
 */
public class FeedBackWindow {
    @FXML
    TextArea feedbacktext, votingname;
    @FXML
    private Menu userName;

    @FXML
    public void initialize(){
        userName.setText(UserSession.getInstance().getLogin());
    }

    /**
     * Navigate to the login window when user want to log out.
     */
    @FXML
    private void PassToLoginWindow(){
        votingname.setVisible(false);
        SceneManager.getInstance().setSetVisibilityofFeed(false);
        UserSession.getInstance().setStarted(null, null);
        LogInController.serializeSession(UserSession.getInstance());
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    /**
     * Navigate to the menu window.
     */
    @FXML
    private void passToMenuWindow(){
        votingname.setVisible(false);
        SceneManager.getInstance().setSetVisibilityofFeed(false);
        SceneManager.getInstance().loadScene("MenuWindow.fxml");
    }

    /**
     * Add new feedback.
     */
    @FXML
    private void addNewFeedback(){
        String votename = votingname.getText();
        String text = feedbacktext.getText();
        String username = UserSession.getInstance().getLogin();
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

    /**
     * Set the visibility of the voting name text field.
     *
     * @param visible The visibility status.
     */
    public void setVisibleTextField(boolean visible){
        votingname.setVisible(visible);
    }
}
