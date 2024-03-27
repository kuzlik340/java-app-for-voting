package com.example.uvs.GUI;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;

import java.io.IOException;

public class VoteWindow implements PassUsername{
    @FXML
    private Button name1, name2, name3, name4;
    @FXML
    private Menu userName;
    private String user;

    @FXML
    private void initialize() {
        settingText();
    }
    @Override
    public void PassUser(String username) {
        this.user = username;
        userName.setText(user);
    }
    @FXML
    private void PassToLoginWindow(){
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }
    private void settingText(){
        //добавить базу данных
        name1.setText("Destroy");
        name2.setText("Loft-project");
        name3.setText("Park");
        name4.setText("Museum");
    }

    @FXML
    private void passVote(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        showVoteWindowEnd(clickedButton.getId());
    }

    private void showVoteWindowEnd(String buttonId) {
        SceneManager.getInstance().loadSceneWithId("VoteWindowEnd.fxml", buttonId); //setting scene with results of voting
                                                                                             //passing window by using loadSceneWithID
    }
}
