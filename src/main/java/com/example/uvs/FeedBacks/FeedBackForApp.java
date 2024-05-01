package com.example.uvs.FeedBacks;

import com.example.uvs.DataBase.DataBaseConnection;

import java.util.List;
import java.util.Map;

public class FeedBackForApp {
    private String login;
    private String feedback;
    List<FeedBackForApp> feedbackMap;
    public void addFeedBack(String feedbackText, String id){
        DataBaseConnection.DataBaseInterface.addFeedback(id, feedbackText);
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


    public  List<FeedBackForApp> getFeedbackText() {
        feedbackMap = DataBaseConnection.DataBaseInterface.getFeedback();
        return feedbackMap;
    }

}
