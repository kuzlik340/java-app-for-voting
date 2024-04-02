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

public class SceneManager {
    private static SceneManager instance = new SceneManager();
    private Stage primaryStage;
    private String user;
    private Map<String, Object> controllers = new HashMap<>();

    private SceneManager() {}

    public static SceneManager getInstance() {
        return instance;
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



    public void loadScene(String fxmlPath){

        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Object controller = loader.getController();
            if (controller instanceof PassUsername) { // Передача username, если контроллер его поддерживает
                ((PassUsername) controller).PassUser(user);
            }
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e){
            showErrorDialog();
        }

    }


//    public void loadSceneWithId(String fxmlPath, String buttonId) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Object controller = loader.getController();
//
//            if (controller instanceof VoteWindowEnd) { // Проверяем, является ли контроллер VoteWindowEnd
//                ((VoteWindowEnd) controller).initData(buttonId); // Передаем buttonId
//            }
//            if (controller instanceof PassUsername) { // Передача username, если контроллер его поддерживает
//                ((PassUsername) controller).PassUser(user);
//            }
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (IOException e) {
//            showErrorDialog();
//        }
//    }

    public void loadSceneWithIdInt(String fxmlPath, int buttonId) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Object controller = loader.getController();

            if (controller instanceof VoteWindow) { // Проверяем, является ли контроллер VoteWindowEnd
                ((VoteWindow) controller).setID(buttonId); // Передаем buttonId
            }
            if (controller instanceof PassUsername) { // Передача username, если контроллер его поддерживает
                ((PassUsername) controller).PassUser(user);
            }
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            showErrorDialog();
        }
    }

    public void showErrorDialog() {
            Alert alert = new Alert(Alert.AlertType.ERROR); // Создаем диалоговое окно с типом ERROR
            alert.setTitle("Error");
            alert.setHeaderText("Unexpected error. Please restart application");
            alert.setContentText(":("); // Устанавливаем сообщение об ошибке

            alert.showAndWait(); // Показываем диалог и ждем, пока пользователь его закроет
    }
    public Object getController(String fxmlPath) {
        return controllers.get(fxmlPath);
    }
}
