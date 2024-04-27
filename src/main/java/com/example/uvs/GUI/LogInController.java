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
        SceneManager.getInstance().setPrimaryStage(primaryStage); //initializing window to open an app
        primaryStage.setResizable(false);
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    @FXML
    private void handleLoginAction(){
        username = usernameField.getText();
        String password = passwordField.getText();
        ErrorLabel.setVisible(false);

        isRegistered = Citizen.checkLogInfo(username, password); //checking if user is registered in system or not
        isAdmin = Citizen.checkAdmin(username, password);
        if (isRegistered) {
            if(isAdmin == 1){  //strategy pattern for regular voter and admin
                actionStrategy = new Administrator(username, password);
            }
            else{
                actionStrategy = new RegularVoter(username, password);
            }
            SceneManager.getInstance().setUsername(username); //pass to menu window
            actionStrategy.performAction(); //start of perfrom action for two types of users
            SceneManager.getInstance().loadScene("MenuWindow.fxml");
            usernameField.clear();  //clearing fields if user will log out
            passwordField.clear();
        } else {
            ErrorLabel.setVisible(true);  //error label to show that user is not registered
            usernameField.setStyle("-fx-border-color: red;");
            passwordField.setStyle("-fx-border-color: red;");
        }

    }

    @FXML
    private void handlePassToCreateAction(){
        SceneManager.getInstance().loadScene("CreateAccWindow.fxml"); //pass to scene where user can create a new account
    }

    public static void main(String[] args) {
        launch();    //launching JavaFX
    }
}
