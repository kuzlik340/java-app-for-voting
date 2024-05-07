package com.example.uvs.FeedBacks;

import com.example.uvs.DataBase.DataBaseConnection;

import java.util.List;

public class FeedBackForApp implements ActionStrategy2{
    private String login;
    private String feedback;
    List<FeedBackForApp> feedbackMap;
    public void addFeedBack(String feedbackText, String username){
        DataBaseConnection.DataBaseInterface.addFeedback("-", username, feedbackText, "FeedBackForAppDB");
    }
    public FeedBackForApp(){

    }
    public FeedBackForApp(String username, String feedback){
        this.login = username;
        this.feedback = feedback;
    }
    public String getLogin(){
        return login;
    }
    public String getFeedText(){
        return feedback;
    }
    public String getNameOfVote(){
        return "";
    }


    public  List<FeedBackForApp> getFeedbackText() {
        feedbackMap = DataBaseConnection.DataBaseInterface.getFeedback("FeedBackForAppDB");
        return feedbackMap;
    }

}
