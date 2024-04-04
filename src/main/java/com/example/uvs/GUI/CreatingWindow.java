package com.example.uvs.GUI;
import com.example.uvs.DataBase.DataBaseConnection;
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
    public void Create() {
        String titleInput = title.getText();
        String textInput = text.getText();
        String option1Input = option1.getText();
        String option2Input = option2.getText();
        String option3Input = option3.getText();
        String option4Input = option4.getText();

        // SQL запрос для вставки новой записи
        String sql = "INSERT INTO votecards(Label, text, option1, option2, option3, option4, voted) VALUES(?, ?, ?, ?, ?, ?, ?)";

        // Использование try-with-resources для автоматического закрытия соединения и PreparedStatement
        try (Connection conn = new DataBaseConnection().connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titleInput);
            pstmt.setString(2, textInput);
            pstmt.setString(3, option1Input);
            pstmt.setString(4, option2Input);
            pstmt.setString(5, option3Input);
            pstmt.setString(6, option4Input);
            pstmt.setBoolean(7, false);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        title.clear();  //clearing fields if user will log out
        text.clear();
        option1.clear();
        option2.clear();
        option3.clear();
        option4.clear();
    }

}
