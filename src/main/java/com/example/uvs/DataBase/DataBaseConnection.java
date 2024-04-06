package com.example.uvs.DataBase;
import com.example.uvs.Vote_cards.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// This class provides the methods for connecting to and interacting with the database.
public class DataBaseConnection {
    // Connects to the SQLite database and returns the connection object.
    public Connection connect(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:sqlite:src/main/resources/com/example/uvs/User.db");
        } catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    // Inner class that contains methods to interact with the database for user and voting operations.
    public static class DataBaseInterface {
        // Checks if a user with the given login and password exists in the database.
        public static boolean checkLogInfo(String login, String password) {
            String sqlSelect = "SELECT * FROM users WHERE login = ? AND password = ?";
            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {

                pstmt.setString(1, login);
                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {
                    return rs.next();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return false;
        }

        // Checks if the user has already voted for a particular vote ID.
        public static boolean checkIfVoted(String login, int voteId) {
            System.out.println("check started with " + login);
            String sqlSelect = "SELECT voted_ids FROM users WHERE login = ?";
            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {

                pstmt.setString(1, login);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        String ids = rs.getString("voted_ids");
                        if(ids.equals("")){
                            return false;
                        }
                        String[] pairs = ids.split(",");
                        for (String pair : pairs) {
                            int ID = Integer.parseInt(pair.trim());
                            if (ID == voteId) {
                                return true;
                            }
                        }
                        return false;
                    } else {
                        return false;
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        // Checks if a user is an admin.
        public static int checkAdmin(String login, String password) {
            String sqlSelect = "SELECT admin FROM users WHERE login = ? AND password = ?";
            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {

                pstmt.setString(1, login);
                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("admin");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return -1; // Returns -1 if user is not found or an error occurs.
        }

        // Adds a new user to the database.
        public static void addUser(String login, String password) {
            String sql = "INSERT INTO users(login, password, admin, voted_ids) VALUES(?, ?, ?, ?)";
            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, login);
                pstmt.setString(2, password);
                pstmt.setBoolean(3, false); // By default, the user is not an admin.
                pstmt.setString(4, "");


                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        // Updates the database to record that a user has voted.
        public static void userVoted(int idOfVote, String idOfOption, String login) {
            String idd = Integer.toString(idOfVote);
            String sql = "UPDATE users SET voted_ids = CASE WHEN voted_ids = '' THEN ? ELSE voted_ids || ',' || ? END WHERE login = ?;";
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

        // Retrieves all voting cards from the database.
        public static List<Card> getCards() {
            List<Card> cards = new ArrayList<>();
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                conn = new DataBaseConnection().connect();
                conn.setAutoCommit(false);

                String sql = "SELECT * FROM votecards";

                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    // Creates a new Card object for each row in the result set.
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
                // Ensures all resources are closed properly.
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

        // Inserts a new voting card into the database.
        public static void CreateCards(String Label, String text, String option1, String option2, String option3, String option4){
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            try {
                conn = new DataBaseConnection().connect();
                String sql = "INSERT INTO votecards(Label, text, option1, option2, option3, option4, option1number, " +
                        "option2number, option3number, option4number, votecounter) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, Label);
                pstmt.setString(2, text);
                pstmt.setString(3, option1);
                pstmt.setString(4, option2);
                pstmt.setString(5, option3);
                pstmt.setString(6, option4);
                pstmt.setInt(7, (int)(Math.random()*100000));
                pstmt.setInt(8, (int)(Math.random()*100000));
                pstmt.setInt(9, (int)(Math.random()*100000));
                pstmt.setInt(10, (int)(Math.random()*100000));
                pstmt.setInt(11, 0);

                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // Ensures all resources are closed properly.
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (conn != null) conn.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        // Retrieves the vote counts for each option of a specific vote card.
        public static List<Integer> getVotes(int idOfVote) {
            List<Integer> votes = new ArrayList<>();
            String sql = "SELECT option1number, option2number, option3number, option4number FROM votecards WHERE ID = ?";

            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, idOfVote);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
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

        // Randomly increases the vote counts for options of a vote card and returns the new total vote count.
        public static int checkIfVoted3times(int idOfVote) {
            String sqlUpdate = "UPDATE votecards SET " +
                    "option1Number = option1Number + ?, " +
                    "option2Number = option2Number + ?, " +
                    "option3Number = option3Number + ?, " +
                    "option4Number = option4Number + ?, " +
                    "votecounter = votecounter + 1 " +
                    "WHERE ID = ?";
            String sqlSelect = "SELECT votecounter FROM votecards WHERE ID = ?";

            int votecounter = -1;

            try (Connection conn = new DataBaseConnection().connect();
                 PreparedStatement pstmtUpdate = conn.prepareStatement(sqlUpdate);
                 PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect)) {

                // Randomly generates vote increases.
                int option1rand = (int)(Math.random()*1000);
                int option2rand = (int)(Math.random()*1000);
                int option3rand = (int)(Math.random()*1000);
                int option4rand = (int)(Math.random()*1000);

                // Updates vote counts.
                pstmtUpdate.setInt(1, option1rand);
                pstmtUpdate.setInt(2, option2rand);
                pstmtUpdate.setInt(3, option3rand);
                pstmtUpdate.setInt(4, option4rand);
                pstmtUpdate.setInt(5, idOfVote);
                pstmtUpdate.executeUpdate();

                // Retrieves the new total vote count.
                pstmtSelect.setInt(1, idOfVote);
                try (ResultSet rs = pstmtSelect.executeQuery()) {
                    if (rs.next()) {
                        votecounter = rs.getInt("votecounter");
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
            return votecounter;
        }
        public static void resetAllDatabase() {
            String sqlResetVoteCounter = "UPDATE votecards SET votecounter = 0"; // Reset votecounter to 0 in all rows
            String sqlResetVotedIds = "UPDATE users SET voted_ids = ''"; // Reset voted_ids to empty string in all rows

            Connection conn = null;
            PreparedStatement pstmtVoteCounter = null;
            PreparedStatement pstmtVotedIds = null;

            try {
                conn = new DataBaseConnection().connect();
                conn.setAutoCommit(false); // Start transaction

                // Reset votecounter in votecards
                pstmtVoteCounter = conn.prepareStatement(sqlResetVoteCounter);
                pstmtVoteCounter.executeUpdate();

                // Reset voted_ids in users
                pstmtVotedIds = conn.prepareStatement(sqlResetVotedIds);
                pstmtVotedIds.executeUpdate();

                conn.commit(); // Commit transaction
            } catch (SQLException e) {
                if (conn != null) {
                    try {
                        conn.rollback(); // Rollback transaction in case of error
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                System.out.println(e.getMessage());
            } finally {
                // Close resources
                try {
                    if (pstmtVoteCounter != null) pstmtVoteCounter.close();
                    if (pstmtVotedIds != null) pstmtVotedIds.close();
                    if (conn != null) conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

    }
}