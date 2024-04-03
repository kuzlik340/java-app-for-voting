package com.example.uvs.DataBase;
import java.sql.*;

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




}
