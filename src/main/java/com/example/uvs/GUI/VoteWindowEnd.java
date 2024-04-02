package com.example.uvs.GUI;

import com.example.uvs.Vote_cards.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import com.example.uvs.Voting_logic.VotingProcess;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VoteWindowEnd implements PassUsername{
    @FXML
    private Label text1, text2, text3, text4;
    @FXML
    private Menu userName;
    private String user;
    @Override
    public void PassUser(String username) {
        this.user = username;
        userName.setText(user);
    }

    @FXML
    private void PassToLoginWindow() {
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }
    public void initData(int buttonId) {
        List<Card> cards = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/example/uvs/User.db");
            conn.setAutoCommit(false);

            // Создаем SQL-запрос
            String sql = "SELECT * FROM votecards WHERE id = buttonId;";

            // Выполняем запрос
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // Итерируем по результатам запроса
                // Извлекаем данные из текущей строки результата
                String text1 = rs.getString("Label");
                String text2 = rs.getString("text");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        // Определяем, какое текстовое поле обновить на основе buttonId
//        switch(buttonId) {
//            case "name1":
//                text1.setText("You voted for: Destroy " + VotingProcess.voting());
//                text2.setText("Loft-project " + VotingProcess.voting());
//                text3.setText("Park " + VotingProcess.voting());
//                text4.setText("Museum " + VotingProcess.voting());
//                break;
//            case "name2":
//                text1.setText("Destroy " + VotingProcess.voting());
//                text2.setText("You voted for: Loft-project " + VotingProcess.voting());
//                text3.setText("Park " + VotingProcess.voting());
//                text4.setText("Museum " + VotingProcess.voting());
//                break;
//            case "name3":
//                text1.setText("Destroy " + VotingProcess.voting());
//                text2.setText("Loft-project " + VotingProcess.voting());
//                text3.setText("You voted for: Park " + VotingProcess.voting());
//                text4.setText("Museum " + VotingProcess.voting());
//                break;
//            case "name4":
//                text1.setText("Destroy " + VotingProcess.voting());
//                text2.setText("Loft-project " + VotingProcess.voting());
//                text3.setText("Park " + VotingProcess.voting());
//                text4.setText("You voted for: Museum " + VotingProcess.voting());
//                break;
//        }

    }
}