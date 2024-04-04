package com.example.uvs.GUI;

import com.example.uvs.Citizen.Citizen;
import com.example.uvs.Citizen.RegularVoter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateNewAccount {
    @FXML
    private TextField usernameField, passwordField;
    @FXML
    public void  handleCreateNewUser(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        Citizen user1 = new RegularVoter(username, password);
        user1.addUser(username, password);
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }
}
