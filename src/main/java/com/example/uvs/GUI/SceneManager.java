package com.example.uvs.GUI;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;
import com.example.uvs.Vote_cards.Card;
//singleton class to manage and show all scenes
public class SceneManager {
    private boolean setVisibility; //variable for visibility of button for creating new voting
    private boolean visibilityOfFeed = false;
    private boolean isFeedbackVoting = false;
    private static SceneManager instance = new SceneManager();
    private Stage primaryStage;
    private String user;  //variable to save username so we can pass username to all windows
                          //that are implementing 'PassUsername' interface

    private SceneManager() {}

    public static SceneManager getInstance() {
        return instance;  //returning singleton class
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public void setUsername(String username) { // Для установки имени пользователя
        this.user = username;
    }
    public String getUsername(){
        return user;
    }
    public void setSetVisibility(boolean setting){
        this.setVisibility = setting;
    }
    public void setSetVisibilityofFeed(boolean setting){
        this.visibilityOfFeed = setting;
        System.out.println(setting);
    }
    public void feedbacktype(boolean type){
        this.isFeedbackVoting = type;
        System.out.println("FROM scenemanager = "+ type);
    }

    public void loadScene(String fxmlPath){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Object controller = loader.getController();
            if (controller instanceof PassUsername) { //pass username if controller implement PAssUsername RTTI
                ((PassUsername) controller).PassUser(user);
            }
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


    public void loadSceneWithIdInt(String fxmlPath, int buttonId, List<Card> votingCards) {
        try {               //using this  method only for 'VoteWindow' cause we have to pass there button id
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Object controller = loader.getController();
            ((VoteWindow) controller).setID(buttonId);
            ((VoteWindow) controller).setList(votingCards);
            ((PassUsername) controller).PassUser(user);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            showErrorDialog();
        }
    }

    public void showErrorDialog() { //error window if something went wrong
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Unexpected error. Please restart application");
            alert.setContentText(":(");
            alert.showAndWait();
    }
}
