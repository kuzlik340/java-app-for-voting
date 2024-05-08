package com.example.uvs.GUI;

import com.example.uvs.Citizen.*;
import com.example.uvs.DataBase.PasswordInCorrectException;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.function.Supplier;

/**
 * The LogInController class controls the login interface and user authentication.
 * It manages user login, password verification, session serialization, and navigation to different screens.
 */
public class LogInController extends Application {
    @FXML
    private TextField usernameField, passwordField;
    @FXML
    private Label ErrorLabelNotRegistered, ErrorLabelIncorrectPassword;
    boolean isRegistered;
    int isAdmin;
    private String username, password;
    private ActionStrategy actionStrategy;

    /**
     * Start method of the JavaFX application.
     *
     * @param primaryStage The primary stage of the application.
     */
    @Override
    public void start(Stage primaryStage) {
        showLogInWindow(primaryStage);
        String[] serializedCredentials = checkifSerialized.get(); //lambda function
        if (serializedCredentials[0] != null && serializedCredentials[1] != null) {
            System.out.println("Serialized found");
            // If serialized credentials exist, perform login action with these credentials
            username = serializedCredentials[0];
            password = serializedCredentials[1];
            isAdmin = Citizen.checkAdmin(username, password);
            if(isAdmin == 1){                      //strategy pattern for regular voter and admin
                actionStrategy = new Administrator(username, password);
            }
            else{
                actionStrategy = new RegularVoter(username, password);
            }
            actionStrategy.showAdminFeatures(); //setting visibility for admin features (true or false)
            UserSession.getInstance().setStarted(serializedCredentials[0], serializedCredentials[1]);
            SceneManager.getInstance().setPrimaryStage(primaryStage); //initializing window to open an app
            primaryStage.setResizable(false);
            SceneManager.getInstance().loadScene("MenuWindow.fxml");
        }
    }

    /**
     * Show the login window.
     *
     * @param primaryStage The primary stage of the application.
     */
    public static void showLogInWindow(Stage primaryStage) {
        SceneManager.getInstance().setPrimaryStage(primaryStage); //initializing window to open an app
        primaryStage.setResizable(false);
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    /**
     * Handle the login action.
     */
    @FXML
    private void handleLoginAction() {
        username = usernameField.getText();
        password = passwordField.getText();
        ErrorLabelNotRegistered.setVisible(false);
        ErrorLabelIncorrectPassword.setVisible(false);
        isRegistered = Citizen.checkLogInfo(username, password); //checking if user is registered in system or not
        isAdmin = Citizen.checkAdmin(username, password);
        try{
            Citizen.checkPassword(username, password);
        }
        catch (PasswordInCorrectException e){
            ErrorLabelIncorrectPassword.setVisible(true);
            //catching own exception that throws only when password is the same for this user
            usernameField.setStyle("-fx-border-color: red;");
            passwordField.setStyle("-fx-border-color: red;");
            System.out.println("incorrect password exception catched");
            return;
        }
        if (isRegistered) {
            if(isAdmin == 1){  //strategy pattern for regular voter and admin
                actionStrategy = new Administrator(username, password);
            }
            else{
                actionStrategy = new RegularVoter(username, password);
            }
            UserSession.getInstance().setStarted(username, password);
            serializeSession(UserSession.getInstance());
            actionStrategy.showAdminFeatures(); //setting visibility for admin features (true or false)
            SceneManager.getInstance().loadScene("MenuWindow.fxml");
            usernameField.clear();  //clearing fields
            passwordField.clear();
        } else {
            ErrorLabelNotRegistered.setVisible(true);  //error label to show that user is not registered
            usernameField.setStyle("-fx-border-color: red;");
            passwordField.setStyle("-fx-border-color: red;");
        }
    }

    /**
     * Serialize the user session.
     *
     * @param session The user session to serialize.
     */
    public static void serializeSession(UserSession session) {
        //Serializing session if user didnt log out so the program will start from the account of previous user
        try {
            FileOutputStream fileOut = new FileOutputStream("session.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(session);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if serialized data exists and return it.
     */
    private Supplier<String[]> checkifSerialized = () -> {
        //lambda function to return serialized data(login and password)
        String[] credentials = new String[2];

        UserSession deserializedSession = deserializeSession();
        if (deserializedSession != null) {
            // Session was started, use deserialized login and password
            credentials[0] = deserializedSession.getLogin();
            credentials[1] = deserializedSession.getPassword();
        }

        return credentials;
    };

    /**
     * Deserialize the user session.
     *
     * @return The deserialized user session.
     */
    private UserSession deserializeSession() {
        UserSession session = null;
        try {
            FileInputStream fileIn = new FileInputStream("session.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            session = (UserSession) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Deserialized data is loaded from session.ser");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return session;
    }

    /**
     * Handle the action to navigate to the create account screen.
     */
    @FXML
    private void handlePassToCreateAction(){
        SceneManager.getInstance().loadScene("CreateAccWindow.fxml"); //pass to scene where user can create a new account
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch();    //launching JavaFX
    }
}
