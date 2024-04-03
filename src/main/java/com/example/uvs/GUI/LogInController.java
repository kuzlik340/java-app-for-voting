package com.example.uvs.GUI;

import com.example.uvs.Citizen.ActionStrategy;
import com.example.uvs.Citizen.Administrator;
import com.example.uvs.Citizen.Citizen;
import com.example.uvs.Citizen.RegularVoter;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class LogInController extends Application {
    @FXML
    private TextField usernameField, passwordField;
    @FXML
    private Label ErrorLabel;
    boolean isRegistered;
    int isAdmin;
    private String username;
    private ActionStrategy actionStrategy;

    @Override
    public void start(Stage primaryStage){
        showLogInWindow(primaryStage);
    }
    public static void showLogInWindow(Stage primaryStage) {
        SceneManager.getInstance().setPrimaryStage(primaryStage); //initializing window to open an app\
        primaryStage.setResizable(false);
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    @FXML
    private void handleLoginAction(){
        username = usernameField.getText();  //getting login information
        String password = passwordField.getText();
        isRegistered = Citizen.checkLogInfo(username, password); //checking if user is registered in system or not
        isAdmin = Citizen.checkAdmin(username, password);
        if (isRegistered) {
            if(isAdmin == 1){
                actionStrategy = new Administrator(username, password);
            }
            else{
                actionStrategy = new RegularVoter(username, password);
            }
            SceneManager.getInstance().setUsername(username); //pass to menu window
            actionStrategy.performAction();
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
