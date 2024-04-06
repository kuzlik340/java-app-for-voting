package com.example.uvs.GUI;

import com.example.uvs.Citizen.Citizen;
import com.example.uvs.Citizen.RegularVoter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateNewAccount {
    @FXML
    private TextField usernameField, passwordField;
    @FXML
    private Label ErrorLabel2, ErrorLabel3;

    @FXML
    public void  handleCreateNewUser(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        ErrorLabel2.setVisible(false);
        ErrorLabel3.setVisible(false);
        if(!isLoginValid(username)){
            usernameField.setStyle("-fx-border-color: red;");  //if login is not valid
            passwordField.setStyle("-fx-border-color: red;");
            ErrorLabel2.setVisible(true);
        }
        if(!isPasswordValid(password) && isLoginValid(username)){
            usernameField.setStyle("-fx-border-color: red;");  //if password is not valid
            passwordField.setStyle("-fx-border-color: red;");
            ErrorLabel3.setVisible(true);
        }
        else if(isPasswordValid(password) && isLoginValid(username)){
            ErrorLabel2.setVisible(false);
            ErrorLabel3.setVisible(false);
            Citizen user1 = new RegularVoter(username, password); //creating new user
            user1.addUser(username, password);  //adding user to database
            SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
        }
    }
    public static boolean isPasswordValid(String password) {
        //checking if password is more than 6 symbols and contains at least one number
        return password.length() >= 6 && password.matches(".*\\d.*");
    }

    public static boolean isLoginValid(String login) {
       //check if login does not contain numbers
        return !login.matches(".*\\d.*");
    }

}
