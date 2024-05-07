package com.example.uvs.GUI;
import com.example.uvs.Citizen.UserSession;
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
        UserSession.getInstance().setStarted(null, null);
        LogInController.serializeSession(UserSession.getInstance());
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
        //setting login scene if user clicked on a button in menu bar
    }

    @Override
    public void PassUser(String username) {
        //pass username to show it in left top side of window
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
        //function to create new voting card
        Card.CreateCards(titleInput, textInput, option1Input, option2Input, option3Input, option4Input);
        title.clear();  //clearing fields if after creating new voting
        text.clear();
        option1.clear();
        option2.clear();
        option3.clear();
        option4.clear();
    }

}
