package com.example.uvs.Citizen;
import java.sql.*;

public abstract class Citizen {
    protected int id;
    protected String name;
    protected String login;
    protected String password;
    protected static String url = "jdbc:sqlite:src/main/resources/com/example/uvs/User.db";

    public Citizen(String login, String password){
        this.login = login;
        this.password = password;
        addUser(login, password);
    }
    public static boolean checkLogInfo(String login, String password) {
        String sqlSelect = "SELECT * FROM users WHERE login = ? AND password = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {


            pstmt.setString(1, login);
            pstmt.setString(2, password);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return true; // Пользователь найден
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false; // Пользователь не найден
    }


    public String getLogin() {
        return login;
    }
    public static void addUser(String login, String password) {
        // SQL запрос для вставки новой записи
        String sql = "INSERT INTO users(login, password) VALUES(?, ?)";

        // Использование try-with-resources для автоматического закрытия соединения и PreparedStatement
        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/example/uvs/User.db");
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


