package com.example.uvs.GUI;

import com.example.uvs.Citizen.Citizen;
import com.example.uvs.Citizen.RegularVoter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * The CreateNewAccount class controls the interface for creating new user accounts.
 */
public class CreateNewAccount {
    @FXML
    private TextField usernameField, passwordField;
    @FXML
    private Label ErrorLabel2, ErrorLabel3;

    /**
     * Handle the creation of a new user account.
     */
    @FXML
    public void handleCreateNewUser(){
        String username = usernameField.getText();
        String password = passwordField.getText();
        ErrorLabel2.setVisible(false);
        ErrorLabel3.setVisible(false);
        if(!isLoginValid(username)){
            usernameField.setStyle("-fx-border-color: red;");
            passwordField.setStyle("-fx-border-color: red;");
            ErrorLabel2.setVisible(true);
        }
        if(!isPasswordValid(password) && isLoginValid(username)){
            usernameField.setStyle("-fx-border-color: red;");
            passwordField.setStyle("-fx-border-color: red;");
            ErrorLabel3.setVisible(true);
        }
        else if(isPasswordValid(password) && isLoginValid(username)){
            ErrorLabel2.setVisible(false);
            ErrorLabel3.setVisible(false);
            Citizen user1 = new RegularVoter(username, password);
            user1.addUser(username, password);
            SceneManager.getInstance().loadScene("LogInWindow.fxml");
        }
    }

    /**
     * Check if the password is valid.
     *
     * @param password The password to validate.
     * @return True if the password is valid, false otherwise.
     */
    public static boolean isPasswordValid(String password) {
        return password.length() >= 6 && password.matches(".*\\d.*");
    }

    /**
     * Check if the username is valid.
     *
     * @param login The username to validate.
     * @return True if the username is valid, false otherwise.
     */
    public static boolean isLoginValid(String login) {
        return !login.matches(".*\\d.*");
    }

}
