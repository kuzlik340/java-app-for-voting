package com.example.uvs.GUI;
import com.example.uvs.Citizen.Citizen;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;



public class CreatingWindow implements PassUsername{
    @FXML
    TextField title, text, option1, option2, option3, option4;
    @FXML
    private Menu userName;
    private String user;
    @FXML
    private void PassToLoginWindow(){
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    @Override
    public void PassUser(String username) {
        this.user = username;
        userName.setText(user);
    }
    @FXML
    private void Create(){

    }

}
