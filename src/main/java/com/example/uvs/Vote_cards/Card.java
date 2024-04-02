package com.example.uvs.Vote_cards;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Card {
    private int IdOfCard = 0;
    private String name;
    private String infoOfObject;


    public Card(String name, String info, int ID){
        this.name = name;
        this.infoOfObject = info;
        this.IdOfCard = ID;
    }
    public static List<Card> getCards() {
        // Предполагается, что класс Card имеет конструктор, принимающий две строки
        List<Card> cards = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/example/uvs/User.db");
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
                int ID = rs.getInt("ID");
                Card card = new Card(text1, text2, ID);
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


    public int getId (Card card) {
        return this.IdOfCard;
    }

    public String getName() {
        return name;
    }
}
