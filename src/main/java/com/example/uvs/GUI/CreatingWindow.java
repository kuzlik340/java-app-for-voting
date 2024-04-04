package com.example.uvs.GUI;
import com.example.uvs.DataBase.DataBaseConnection;
import com.example.uvs.Vote_cards.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
    private void passToMenuWindow(){
        SceneManager.getInstance().loadScene("MenuWindow.fxml");
    }
    @FXML
    public void Create() {
        String titleInput = title.getText();
        String textInput = text.getText();
        String option1Input = option1.getText();
        String option2Input = option2.getText();
        String option3Input = option3.getText();
        String option4Input = option4.getText();

        Card.CreateCards(titleInput, textInput, option1Input, option2Input, option3Input, option4Input);
        title.clear();  //clearing fields if user will log out
        text.clear();
        option1.clear();
        option2.clear();
        option3.clear();
        option4.clear();
    }

}
