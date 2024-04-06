package com.example.uvs;
import com.example.uvs.DataBase.DataBaseConnection;
import com.example.uvs.GUI.LogInController;
public class Main {
    //some information for login. If you want to see admin feature please put in login 'Admin' and in password 'qwerty123'
    public static void main(String[] args) {  //main class that starts an application
        //DataBaseConnection.DataBaseInterface.resetAllDatabase();   //you can use this method to restart all votings
        LogInController.main(args);  //it has to be in class main so Maven can build a jar file
    }
}
