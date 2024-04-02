package com.example.uvs.GUI;
import com.example.uvs.Citizen.Citizen;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;



public class CreatingWindow {
    @FXML
    TextField title, text, option1, option2, option3, option4;
    @FXML
    private void PassToLoginWindow(){
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    @FXML
    private void Create(){

    }

}
