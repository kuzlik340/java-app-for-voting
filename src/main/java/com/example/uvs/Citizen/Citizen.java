package com.example.uvs.Citizen;
import com.example.uvs.DataBase.DataBaseConnection;

public abstract class Citizen {
    protected int id;
    protected String name;
    protected String login;
    protected String password;

    public Citizen(String login, String password){
        this.login = login;
        this.password = password;
    }
    public static boolean checkLogInfo(String login, String password) {
        return DataBaseConnection.DataBaseInterface.checkLogInfo(login, password);
    }

    public static int checkAdmin(String login, String password) {
        return DataBaseConnection.DataBaseInterface.checkAdmin(login, password);
    }

    public String getLogin() {
        return login;
    }
    public void addUser(String login, String password) {
        DataBaseConnection.DataBaseInterface.addUser(login, password);
    }
    public abstract void performAction();


}


