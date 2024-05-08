package com.example.uvs.GUI;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import com.example.uvs.Vote_cards.Card;

/**
 * The SceneManager class manages and shows all scenes in the application.
 * It provides methods to load different FXML files, set visibility for certain UI elements,
 * pass data between scenes, and handle errors.
 */
public class SceneManager {
    private boolean setVisibility; //variable for visibility of button for creating new voting
    private boolean visibilityOfFeed = false;
    private boolean isFeedbackVoting = false;
    private static SceneManager instance = new SceneManager();
    private Stage primaryStage;
    private String user;  //variable to save username so we can pass username to all windows
    //that are implementing 'PassUsername' interface

    private SceneManager() {}

    /**
     * Get the singleton instance of the SceneManager class.
     *
     * @return The singleton instance of SceneManager.
     */
    public static SceneManager getInstance() {
        return instance;  //returning singleton class
    }

    /**
     * Set the primary stage of the application.
     *
     * @param primaryStage The primary stage.
     */
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Set the username.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.user = username;
    }

    /**
     * Get the username.
     *
     * @return The username.
     */
    public String getUsername(){
        return user;
    }

    /**
     * Set the visibility for creating new voting.
     *
     * @param setting The visibility setting.
     */
    public void setSetVisibility(boolean setting){
        this.setVisibility = setting;
    }

    /**
     * Set the visibility for feedback.
     *
     * @param setting The visibility setting.
     */
    public void setSetVisibilityofFeed(boolean setting){
        this.visibilityOfFeed = setting;
    }

    /**
     * Set the feedback type.
     *
     * @param type The feedback type.
     */
    public void feedbacktype(boolean type){
        this.isFeedbackVoting = type;
    }

    /**
     * Load a scene with the specified FXML file path.
     *
     * @param fxmlPath The FXML file path.
     */
    public void loadScene(String fxmlPath){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Object controller = loader.getController();
            if (fxmlPath.equals("MenuWindow.fxml")) { //setting visibility of button that is for admin
                ((MenuWindow) controller).setVisibleCreating(setVisibility);
            }
            if (fxmlPath.equals("FeedBackWindow.fxml")) { //setting visibility of button that is for admin
                ((FeedBackWindow) controller).setVisibleTextField(visibilityOfFeed);
            }
            if (fxmlPath.equals("FeedBackListWindow.fxml")) { //setting visibility of button that is for admin
                ((FeedBackListsWindow) controller).setType(isFeedbackVoting);
            }

            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e){
            showErrorDialog();
        }

    }

    /**
     * Load a scene with the specified FXML file path and pass an integer ID and a list of voting cards.
     *
     * @param fxmlPath The FXML file path.
     * @param buttonId The ID of the voting card.
     * @param votingCards The list of voting cards.
     */
    public void loadSceneWithIdInt(String fxmlPath, int buttonId, List<Card> votingCards) {
        try {               //using this  method only for 'VoteWindow' cause we have to pass there button id
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Object controller = loader.getController();
            ((VoteWindow) controller).setID(buttonId);
            ((VoteWindow) controller).setList(votingCards);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            showErrorDialog();
        }
    }

    /**
     * Show an error dialog.
     */
    public void showErrorDialog() { //error window if something went wrong
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Unexpected error. Please restart application");
        alert.setContentText(":(");
        alert.showAndWait();
    }
}
