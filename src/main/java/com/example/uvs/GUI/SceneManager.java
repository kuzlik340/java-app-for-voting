package com.example.uvs.GUI;

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

    private SceneManager() {}

    /**
     * Get the singleton instance of the SceneManager class.
     *
     * @return The singleton instance of SceneManager.
     */
    public static SceneManager getInstance() {
        return instance;
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
     * Set the visibility for admin features.
     *
     * @param setting The visibility setting.
     */
    public void setSetVisibility(boolean setting){
        this.setVisibility = setting;
    }

    /**
     * Set the visibility for feedback.
     * Method to set visibility of text area for name of voting for user
     * @param setting The visibility setting.
     */
    public void setSetVisibilityofFeed(boolean setting){
        this.visibilityOfFeed = setting;
    }

    /**
     * Set the feedback type.
     * This method is used to show the feedbacks for admin and the boolean describes what type
     * of feedback will be shown, true - voting feedbacks, false feedbacks on app
     * @param type The feedback type.
     */
    public void feedbacktype(boolean type){
        this.isFeedbackVoting = type;
    }

    /**
     * Load a scene with the specified FXML file path.
     * Also by using 'instanceof' construction we are keeping safety on loading screen
     * Here I used RTTI not for serious purpose, but only for safety of code
     * @param fxmlPath The FXML file path.
     */
    public void loadScene(String fxmlPath){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Object controller = loader.getController();

            // Use instanceof to check and cast to the appropriate controller type
            if (controller instanceof MenuWindow) {
                if (fxmlPath.equals("MenuWindow.fxml")) {
                    ((MenuWindow) controller).setVisibleCreating(setVisibility);
                }
            }
            if (controller instanceof FeedBackWindow) {
                if (fxmlPath.equals("FeedBackWindow.fxml")) {
                    ((FeedBackWindow) controller).setVisibleTextField(visibilityOfFeed);
                }
            }
            if (controller instanceof FeedBackListsWindow) {
                if (fxmlPath.equals("FeedBackListWindow.fxml")) {
                    ((FeedBackListsWindow) controller).setType(isFeedbackVoting);
                }
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
     * Shows an error dialog if something went wrong.
     */
    public void showErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Unexpected error. Please restart application");
        alert.setContentText(":(");
        alert.showAndWait();
    }
}
