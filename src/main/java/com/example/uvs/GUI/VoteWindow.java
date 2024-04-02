package com.example.uvs.GUI;

import com.example.uvs.Vote_cards.Card;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoteWindow implements PassUsername{
    @FXML
    private Button name1, name2, name3, name4;
    @FXML
    private Menu userName;
    @FXML
    private Label  MainLabel;
    @FXML
    private TextArea MainText;

    private String user;
    private String maintextfromDB, mainlabelfromDB, option1, option2, option3, option4;
    private int id;


    @Override
    public void PassUser(String username) {
        this.user = username;
        userName.setText(user);
    }
    @FXML
    private void PassToLoginWindow(){
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    public void setID(int ID) {
        this.id = ID;
        settingTextFromDB(id);
    }

    public void settingTextFromDB(int id) {
        // Переменные для хранения полученных данных, инициализация значений будет ниже
        System.out.println(id);

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/example/uvs/User.db");
             PreparedStatement prst = conn.prepareStatement("SELECT * FROM votecards WHERE id = ?")) {

            prst.setInt(1, id);
            try (ResultSet rs = prst.executeQuery()) { // Удаление sql из executeQuery()
                if (rs.next()) { // Убедитесь, что результат существует
                    maintextfromDB = rs.getString("text");
                    mainlabelfromDB = rs.getString("Label");
                    option1 = rs.getString("option1");
                    option2 = rs.getString("option2");
                    option3 = rs.getString("option3");
                    option4 = rs.getString("option4");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка исключения SQLException
        }

        MainLabel.setText(mainlabelfromDB);
        MainText.setText(maintextfromDB);
        name1.setText(option1);
        name2.setText(option2);
        name3.setText(option3);
        name4.setText(option4);
        // Закрытие ресурсов не требуется, так как используется try-with-resources
    }


    @FXML
    private void passVote(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        showVoteWindowEnd(clickedButton.getId());
    }

    private void showVoteWindowEnd(String buttonId) {
        SceneManager.getInstance().loadSceneWithIdInt("VoteWindowEnd.fxml", 1); //setting scene with results of voting
    }
}
