package com.example.uvs;

import com.example.uvs.DataBase.DataBaseConnection;
import com.example.uvs.GUI.LogInController;

/**
 * The Main class serves as the entry point for the application.
 * It initializes the login interface and starts the application.
 */
public class Main {

    /**
     * The main method of the application.
     * In this method you can also reset database if you want by just uncommenting line
     */
    public static void main(String[] args) {
        //DataBaseConnection.DataBaseInterface.resetAllDatabase();   //you can use this method to restart all votings
        LogInController.startOfApp(args);  //it has to be in class main so Maven can build a jar file
    }
}
