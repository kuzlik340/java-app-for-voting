package com.example.uvs.FeedBacks;

import com.example.uvs.DataBase.DataBaseConnection;

import java.util.List;

/**
 * Represents feedback for the application.
 */
public class FeedBackForApp implements ActionStrategy2 {
    private String login;
    private String feedback;
    List<FeedBackForApp> feedbackMap;

    /**
     * Adds feedback for the application.
     *
     * @param feedbackText  The feedback text.
     * @param username      The username.
     */
    public void addFeedBack(String feedbackText, String username){
        DataBaseConnection.DataBaseInterface.addFeedback("-", username, feedbackText, "FeedBackForAppDB");
    }

    /**
     * Default constructor for FeedBackForApp.
     */
    public FeedBackForApp(){

    }

    /**
     * Constructs a FeedBackForApp object with specified username and feedback.
     *
     * @param username  The username.
     * @param feedback  The feedback.
     */
    public FeedBackForApp(String username, String feedback){
        this.login = username;
        this.feedback = feedback;
    }

    /**
     * Gets the login.
     *
     * @return The login.
     */
    public String getLogin(){
        return login;
    }

    /**
     * Gets the feedback text.
     *
     * @return The feedback text.
     */
    public String getFeedText(){
        return feedback;
    }

    /**
     * Gets the name of the voting.
     *
     * @return The empty string.
     */
    public String getNameOfVote(){
        return "";
    }

    /**
     * Retrieves the feedback text for the application.
     *
     * @return A list of feedback for the application.
     */
    public List<FeedBackForApp> getFeedbackText() {
        feedbackMap = DataBaseConnection.DataBaseInterface.getFeedback("FeedBackForAppDB");
        return feedbackMap;
    }

}
