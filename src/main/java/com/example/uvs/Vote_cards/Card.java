package com.example.uvs.Vote_cards;
import com.example.uvs.DataBase.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Card {
    private int IdOfCard;
    private String name, infoOfObject, option1, option2, option3, option4;


    public Card(String name, String info, int ID, String option1, String option2, String option3, String option4){
        this.name = name;
        this.infoOfObject = info;
        this.IdOfCard = ID;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
    }
    public static List<Card> getCards() {
        // Предполагается, что класс Card имеет конструктор, принимающий две строки
        List<Card> cards = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = new DataBaseConnection().connect();
            conn.setAutoCommit(false);

            // Создаем SQL-запрос
            String sql = "SELECT * FROM votecards";

            // Выполняем запрос
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // Итерируем по результатам запроса
            while (rs.next()) {
                // Извлекаем данные из текущей строки результата
                String text1 = rs.getString("Label");
                String text2 = rs.getString("text");
                String option1 = rs.getString("option1");
                String option2 = rs.getString("option2");
                String option3 = rs.getString("option3");
                String option4 = rs.getString("option4");
                int ID = rs.getInt("ID");
                Card card = new Card(text1, text2, ID, option1, option2, option3, option4);
                cards.add(card);
            }
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
        return cards;
    }
public static void CreateCards(String Label, String text, String option1, String option2, String option3, String option4){
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

        try {
        conn = new DataBaseConnection().connect();

        // Создаем SQL-запрос
        String sql = "INSERT INTO votecards(Label, text, option1, option2, option3, option4) VALUES(?, ?, ?, ?, ?, ?)";

        // Выполняем запрос

        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, Label);
        pstmt.setString(2, text);
        pstmt.setString(3, option1);
        pstmt.setString(4, option2);
        pstmt.setString(5, option3);
        pstmt.setString(6, option4);
        pstmt.executeUpdate();

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
}


    public int getId (Card card) {
        return this.IdOfCard;
    }
    public String getLabel (Card card) {
        return this.name;
    }
    public String getText (Card card) {
        return this.infoOfObject;
    }
    public List<String> getOptions (Card card) {
        List<String> alloptions = new ArrayList<>();
        alloptions.add(option1);
        alloptions.add(option2);
        alloptions.add(option3);
        alloptions.add(option4);
        return alloptions;
    }


    public String getName() {
        return name;
    }
}
