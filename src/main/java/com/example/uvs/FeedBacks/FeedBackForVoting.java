package com.example.uvs.FeedBacks;

import com.example.uvs.DataBase.DataBaseConnection;

import java.util.List;

public class FeedBackForVoting extends FeedBackForApp{
    private String id;
    private String feedback;
    private String nameVote;
    public void addFeedBack(String nameOfVote, String feedbackText, String id){
        DataBaseConnection.DataBaseInterface.addFeedback(nameOfVote, id, feedbackText, "FeedBackforVotings");
    }
    public FeedBackForVoting(String id, String feedback, String nameVote) {
          this.id = id;
          this.feedback = feedback;
          this.nameVote = nameVote;
    }

    @Override
    public List<FeedBackForApp> getFeedbackText() {
        feedbackMap = DataBaseConnection.DataBaseInterface.getFeedback("FeedBackforApp");
        return feedbackMap;
    }

}
