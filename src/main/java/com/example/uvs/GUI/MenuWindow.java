package com.example.uvs.GUI;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import java.io.IOException;


public class MenuWindow implements PassUsername{
    @FXML
    private Button name1, name2, name3;
    @FXML
    private Menu userName;

    private String user;


    @FXML
    private void initialize() {
        System.out.println("init"+user);
        settingText();
    }


    private void settingText(){
        name1.setText("Abondoned builduing in center");
        name2.setText("Abondoned builduing in Ruzinov");
        name3.setText("Abondoned builduing in Karlova ves");
    }
    @Override
    public void PassUser(String username) {
        this.user = username;
        userName.setText(user);
    }

    @FXML
    private void PassToVoteWindow(){
        SceneManager.getInstance().loadScene("VoteWindow.fxml"); //setting login scene
    }
    @FXML
    private void PassToLoginWindow(){
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

}
