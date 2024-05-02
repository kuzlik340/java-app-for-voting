package com.example.uvs.FeedBacks;

import com.example.uvs.DataBase.DataBaseConnection;

import java.util.List;

public class FeedBackForApp implements ActionStrategy2{
    private String login;
    private String id;
    private String feedback;
    List<FeedBackForApp> feedbackMap;
    public void addFeedBack(String feedbackText, String id){
        DataBaseConnection.DataBaseInterface.addFeedback("-", id, feedbackText, "FeedBackforApp");
    }
    public FeedBackForApp(){

    }
    public FeedBackForApp(String id, String feedback){
        this.login = id;
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
        feedbackMap = DataBaseConnection.DataBaseInterface.getFeedback("FeedBackforApp");
        return feedbackMap;
    }

}
