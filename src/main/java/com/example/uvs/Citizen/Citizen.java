package com.example.uvs.Citizen;
import com.example.uvs.DataBase.DataBaseConnection;

import java.sql.*;

public abstract class Citizen {
    protected int id;
    protected String name;
    protected String login;
    protected String password;

    public Citizen(String login, String password){
        this.login = login;
        this.password = password;
        addUser(login, password);
    }
    public static boolean checkLogInfo(String login, String password) {
        String sqlSelect = "SELECT * FROM users WHERE login = ? AND password = ?";
        try (Connection conn = new DataBaseConnection().connect();
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {

            pstmt.setString(1, login);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next(); // Пользователь найден или нет
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false; // Пользователь не найден или ошибка
    }



    public String getLogin() {
        return login;
    }
    public static void addUser(String login, String password) {
        // SQL запрос для вставки новой записи
        String sql = "INSERT INTO users(login, password) VALUES(?, ?)";

        // Использование try-with-resources для автоматического закрытия соединения и PreparedStatement
        try (Connection conn = new DataBaseConnection().connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public abstract void performAction();


}


