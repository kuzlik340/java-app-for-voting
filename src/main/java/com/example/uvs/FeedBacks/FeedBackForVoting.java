package com.example.uvs.FeedBacks;

import com.example.uvs.DataBase.DataBaseConnection;

import java.util.List;

public class FeedBackForVoting extends FeedBackForApp implements ActionStrategy2{
    private String login;
    private String feedback;
    private String nameVote;
    public void addFeedBack(String nameOfVote, String feedbackText, String id){
        DataBaseConnection.DataBaseInterface.addFeedback(nameOfVote, id, feedbackText, "FeedBackForVotingsDB");
    }
    public FeedBackForVoting(String username, String feedback, String nameVote) {
          this.login = username;
          this.feedback = feedback;
          this.nameVote = nameVote;
    }
    public FeedBackForVoting(){

    }

    public String getLogin(){
        return this.login;
    }
    public String getFeedText(){
        return this.feedback;
    }
    public String getNameOfVote(){
        return this.nameVote;
    }
    @Override
    public List<FeedBackForApp> getFeedbackText() {
        //polymorphism for the function
        feedbackMap = DataBaseConnection.DataBaseInterface.getFeedback("FeedBackForVotingsDB");
        return feedbackMap;
    }

}
