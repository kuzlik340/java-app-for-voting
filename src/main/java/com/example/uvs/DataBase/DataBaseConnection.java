package com.example.uvs.DataBase;
import com.example.uvs.Vote_cards.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {
    public Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/example/uvs/User.db");

        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;

    }


    public static class DataBaseInterface {
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
        public static boolean checkIfVoted(String login, int voteId) {
            System.out.println("check started with " + login);
            String sqlSelect = "SELECT voted_ids FROM users WHERE login = ?";
            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {

                pstmt.setString(1, login);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("started if");
                        String ids = rs.getString("voted_ids");
                        if(ids.equals("")){
                            return false;
                        }
                        System.out.println("database" + ids);
                        String[] pairs = ids.split(",");
                        for (String pair : pairs) {
                            int ID = Integer.parseInt(pair.trim());
                            System.out.println("parseid" +  ID);
                            if (ID == voteId) {
                                System.out.println("ID found" + ID + " " + voteId);
                                return true;
                            }
                        }
                        // Если пара с указанным voteId не найдена
                        return false;
                    } else {
                        // Пользователь не найден
                        return false;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }



        public static int checkAdmin(String login, String password) {
            // Запрос к базе данных теперь включает извлечение колонки 'admin'
            String sqlSelect = "SELECT admin FROM users WHERE login = ? AND password = ?";
            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {

                pstmt.setString(1, login);
                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {
                    // Проверяем, найден ли пользователь и возвращаем значение из колонки 'admin'
                    if (rs.next()) {
                        return rs.getInt("admin"); // Возвращает 1, если пользователь - админ, 0 если не админ
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return -1; // Возвращает -1, если пользователь не найден или произошла ошибка
        }

        public static void addUser(String login, String password) {
            // SQL запрос для вставки новой записи
            String sql = "INSERT INTO users(login, password, admin) VALUES(?, ?, ?)";

            // Использование try-with-resources для автоматического закрытия соединения и PreparedStatement
            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, login);
                pstmt.setString(2, password);
                pstmt.setBoolean(3, false);

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        public static void userVoted(int idOfVote, String idOfOption, String login) {
            String idd = Integer.toString(idOfVote);
            // SQL запрос для вставки новой записи
            String sql = "UPDATE users SET voted_ids = CASE WHEN voted_ids = '' THEN ? ELSE voted_ids || ',' || ? END WHERE login = ?;";
            // Использование try-with-resources для автоматического закрытия соединения и PreparedStatement
            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, idd);
                pstmt.setString(2, idd);
                pstmt.setString(3, login);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
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
        public static List<Integer> getVotes(int idOfVote) {
            List<Integer> votes = new ArrayList<>();
            String sql = "SELECT option1number, option2number, option3number, option4number FROM votecards WHERE ID = ?";

            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, idOfVote);
                ResultSet rs = pstmt.executeQuery();

                // Итерируем по результатам запроса
                if (rs.next()) {
                    // Извлекаем данные из текущей строки результата
                    votes.add(rs.getInt("option1number"));
                    votes.add(rs.getInt("option2number"));
                    votes.add(rs.getInt("option3number"));
                    votes.add(rs.getInt("option4number"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return votes;
        }

        public static int checkIfVoted3times(int idOfVote) {
            // SQL запрос для обновления записи и инкремента счетчика голосов
            String sqlUpdate = "UPDATE votecards SET " +
                    "option1Number = option1Number + ?, " +
                    "option2Number = option2Number + ?, " +
                    "option3Number = option3Number + ?, " +
                    "option4Number = option4Number + ?, " +
                    "votecounter = votecounter + 1 " +
                    "WHERE ID = ?";
            // SQL запрос для получения текущего значения votecounter
            String sqlSelect = "SELECT votecounter FROM votecards WHERE ID = ?";
            // Инициализация votecounter значением -1, которое будет использоваться в случае ошибки
            int votecounter = -1;

            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate);
                 PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {

                // Генерация случайных значений для каждой опции
                int option1rand = (int)(Math.random()*1000);
                int option2rand = (int)(Math.random()*1000);
                int option3rand = (int)(Math.random()*1000);
                int option4rand = (int)(Math.random()*1000);

                // Установка значений для обновления votecards
                pstmtUpdate.setInt(1, option1rand);
                pstmtUpdate.setInt(2, option2rand);
                pstmtUpdate.setInt(3, option3rand);
                pstmtUpdate.setInt(4, option4rand);
                pstmtUpdate.setInt(5, idOfVote);
                pstmtUpdate.executeUpdate();

                // Установка значения для получения votecounter
                pstmtSelect.setInt(1, idOfVote);
                try (ResultSet rs = pstmtSelect.executeQuery()) {
                    if (rs.next()) {
                        votecounter = rs.getInt("votecounter");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            // Возвращаем текущее значение votecounter
            return votecounter;
        }

    }
}
