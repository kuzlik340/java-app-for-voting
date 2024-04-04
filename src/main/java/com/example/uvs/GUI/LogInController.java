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
    private Label ErrorLabel, ErrorLabel2, ErrorLabel3;
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
        username = usernameField.getText();
        String password = passwordField.getText();
        ErrorLabel.setVisible(false);
        ErrorLabel2.setVisible(false);
        ErrorLabel3.setVisible(false);

        if(!isLoginValid(username)){
            usernameField.setStyle("-fx-border-color: red;");  //if user is not registered
            passwordField.setStyle("-fx-border-color: red;");
            ErrorLabel2.setVisible(true);
        }
        if(!isPasswordValid(password) && isLoginValid(username)){
            usernameField.setStyle("-fx-border-color: red;");  //if user is not registered
            passwordField.setStyle("-fx-border-color: red;");
            ErrorLabel3.setVisible(true);
        }

        isRegistered = Citizen.checkLogInfo(username, password); //checking if user is registered in system or not
        isAdmin = Citizen.checkAdmin(username, password);
        if (isRegistered && isPasswordValid(password) && isLoginValid(username)) {
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
        } else if(isPasswordValid(password) && isLoginValid(username)){
            ErrorLabel.setVisible(true);
            usernameField.setStyle("-fx-border-color: red;");  //if user is not registered
            passwordField.setStyle("-fx-border-color: red;");
        }

    }
    public static boolean isPasswordValid(String password) {
        // Проверяем, что длина пароля не менее 6 символов и содержит хотя бы одну цифру
        return password.length() >= 6 && password.matches(".*\\d.*");
    }

    public static boolean isLoginValid(String login) {
        // Проверяем, что логин не содержит цифр
        return !login.matches(".*\\d.*");
    }

    @FXML
    private void handlePassToCreateAction(){
        SceneManager.getInstance().loadScene("CreateAccWindow.fxml"); //setting login scene
    }

    public static void main(String[] args) {
        launch();
    }
}
