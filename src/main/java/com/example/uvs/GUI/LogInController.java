package com.example.uvs.GUI;

import com.example.uvs.Citizen.Citizen;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController extends Application {
    @FXML
    private TextField usernameField, passwordField;
    @FXML
    private Label ErrorLabel;
    boolean isRegistered;
    private String username;

    @Override
    public void start(Stage primaryStage){
        showLogInWindow(primaryStage);
    }
    public static void showLogInWindow(Stage primaryStage) {
        SceneManager.getInstance().setPrimaryStage(primaryStage); //initializing window to open an app
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    @FXML
    private void handleLoginAction(){
        username = usernameField.getText();  //getting login information
        String password = passwordField.getText();
        isRegistered = Citizen.checkLogInfo(username, password); //checking if user is registered in system or not
        if (isRegistered) {
            SceneManager.getInstance().setUsername(username);  //pass to menu window
            SceneManager.getInstance().loadScene("MenuWindow.fxml");
            usernameField.clear();  //clearing fields if user will log out
            passwordField.clear();
        } else {
            ErrorLabel.setVisible(true);
            usernameField.setStyle("-fx-border-color: red;");  //if user is not registered
            passwordField.setStyle("-fx-border-color: red;");
        }

    }

    @FXML
    private void handlePassToCreateAction(){
        SceneManager.getInstance().loadScene("CreateAccWindow.fxml"); //setting login scene
    }

    public static void main(String[] args) {
        launch();
    }
}
