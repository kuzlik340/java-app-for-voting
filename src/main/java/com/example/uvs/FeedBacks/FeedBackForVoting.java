package com.example.uvs.FeedBacks;

import com.example.uvs.DataBase.DataBaseConnection;

import java.util.List;

/**
 * Represents feedback for voting, extending the {@link FeedBackForApp} class and implementing {@link ActionStrategy2}.
 */
public class FeedBackForVoting extends FeedBackForApp implements ActionStrategy2 {
    private String login;
    private String feedback;
    private String nameVote;

    /**
     * Adds feedback for a voting.
     *
     * @param nameOfVote    The name of the voting.
     * @param feedbackText  The feedback text.
     * @param id            The user ID.
     */
    public void addFeedBack(String nameOfVote, String feedbackText, String id){
        DataBaseConnection.DataBaseInterface.addFeedback(nameOfVote, id, feedbackText, "FeedBackForVotingsDB");
    }

    /**
     * Constructs a FeedBackForVoting object with specified username, feedback, and voting name.
     *
     * @param username  The username.
     * @param feedback  The feedback.
     * @param nameVote  The name of the voting.
     */
    public FeedBackForVoting(String username, String feedback, String nameVote) {
        this.login = username;
        this.feedback = feedback;
        this.nameVote = nameVote;
    }

    /**
     * Default constructor for FeedBackForVoting.
     */
    public FeedBackForVoting(){

    }

    /**
     * Gets the login.
     *
     * @return The login.
     */
    public String getLogin(){
        return this.login;
    }

    /**
     * Gets the feedback text.
     *
     * @return The feedback text.
     */
    public String getFeedText(){
        return this.feedback;
    }

    /**
     * Gets the name of the voting.
     *
     * @return The name of the voting.
     */
    public String getNameOfVote(){
        return this.nameVote;
    }

    /**
     * Retrieves the feedback text for voting.
     *
     * @return A list of feedback for voting.
     */
    @Override
    public List<FeedBackForApp> getFeedbackText() {
        feedbackMap = DataBaseConnection.DataBaseInterface.getFeedback("FeedBackForVotingsDB");
        return feedbackMap;
    }
}
