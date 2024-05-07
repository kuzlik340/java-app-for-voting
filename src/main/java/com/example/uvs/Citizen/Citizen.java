package com.example.uvs.Citizen;
import com.example.uvs.DataBase.DataBaseConnection;
import com.example.uvs.DataBase.PasswordInCorrectException;

public abstract class Citizen {
    protected int id;    //abstract class for voters and admin
    protected String name;
    protected String login;
    protected String password;

    public Citizen(String login, String password){
        this.login = login;
        this.password = password;   //constructor to pass password and username
    }
    public static boolean checkLogInfo(String login, String password) {
        return DataBaseConnection.DataBaseInterface.checkLogInfo(login, password);
    }  //method to check if user is registered

    public static int checkAdmin(String login, String password) {
        return DataBaseConnection.DataBaseInterface.checkAdmin(login, password);
    }  //method to check if user is admin

    public static boolean checkPassword(String login, String password) throws PasswordInCorrectException {
        return DataBaseConnection.DataBaseInterface.checkPassword(login, password);
    }

    public String getLogin() {
        return login;
    } //get username info
    public void addUser(String login, String password) {
        DataBaseConnection.DataBaseInterface.addUser(login, password);
    } //method to add a new user to system
    public abstract void showAdminFeatures();


}


