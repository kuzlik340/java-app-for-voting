package com.example.uvs.GUI;

import com.example.uvs.Citizen.UserSession;
import com.example.uvs.DataBase.DataBaseConnection;
import com.example.uvs.Vote_cards.Card;
import com.example.uvs.Voting_logic.VotingProcess;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

/**
 * The VoteWindow class controls the voting window.
 * It displays the voting options, allows users to vote, and allows user to send a feedback on a voting.
 */
public class VoteWindow{
    @FXML
    private Button name1, name2, name3, name4;
    @FXML
    private Menu userName;
    @FXML
    private Label  MainLabel;
    @FXML
    private TextArea MainText;
    @FXML
    private AnchorPane anchorPane;

    private String user;
    private String maintextfromDB, mainlabelfromDB, option1, option2, option3, option4;
    private int id;
    private boolean alreadyVotedByThisuser = false;
    private boolean voteEnded = false;
    private int i;
    List<Card> votingOpt = new ArrayList<>();
    @FXML
    ScrollPane scrollBar;


    /**
     * Navigate to the login window when user want to log out.
     */
    @FXML
    private void PassToLoginWindow(){
        UserSession.getInstance().setStarted( null, null);
        LogInController.serializeSession(UserSession.getInstance());
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }


    @FXML
    private void initialize(){
        System.out.println("initialize");
        scrollBar.setVvalue(-1.0); //setting scroll bar to top
        //pass username to show it in left top side of window
        userName.setText(UserSession.getInstance().getLogin());
        this.user = UserSession.getInstance().getLogin();
        //checking if user already voted for this voting
        System.out.println("check for vote from user = " + alreadyVotedByThisuser + "vote ended"+ voteEnded);
        if (alreadyVotedByThisuser && !voteEnded){
            //to display on window 'You already voted for this'
            replaceButtonsWithLabel();
        }
        else if(voteEnded) {
            //to show the results of voting
            showVoteWindowEnd("0");
        }
    }

    /**
     * Set the ID of the voting card.
     *
     * @param ID The ID.
     */
    public void setID(int ID) {
        System.out.println("setting id of vote");
        //setting id of what voting to show from database
        this.id = ID;
        alreadyVotedByThisuser = VotingProcess.checkIfVoted(id, user);
        voteEnded = VotingProcess.checkIfVoteEnded(id);
    }

    /**
     * Set the list of voting cards.
     *
     * @param votingOpt The list of voting cards.
     */
    public void setList(List<Card> votingOpt){
        this.votingOpt = votingOpt;
        settingTextFromDB(id);
    }

    /**
     * Set the text from the database for the current voting.
     *
     * @param id The ID of the voting card.
     */
    public void settingTextFromDB(int id) {
        //setting all text on vote window
        for(Card card: votingOpt){
            if(card.getId(card) == id){
                mainlabelfromDB = card.getName(card);
                maintextfromDB = card.getText(card);
                List<String>options;
                options = card.getOptions(card);
                option1 = options.get(0);
                option2 = options.get(1);
                option3 = options.get(2);
                option4 = options.get(3);
                break;
            }
        }
        MainLabel.setText(mainlabelfromDB);
        MainText.setText(maintextfromDB);
        name1.setText(option1);
        name2.setText(option2);
        name3.setText(option3);
        name4.setText(option4);
        if (alreadyVotedByThisuser && !voteEnded){
            //to display on window 'You already voted for this'
            replaceButtonsWithLabel();
        }
        else if(voteEnded) {
            //to show the results of voting
            showVoteWindowEnd("0");
        }
    }

    /**
     * Handle voting actions.
     *
     * @param event The ActionEvent.
     */
    @FXML
    protected void passVote(ActionEvent event) {
        System.out.println("Starting all voting process");
        //if some option was clicked we show the result of voting
        VotingProcess.startVotingTimer(id);
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();
        showVoteWindowEnd(buttonId);
        DataBaseConnection.DataBaseInterface.userVoted(id, buttonId, user);
    }

    /**
     * Navigate to the menu window.
     */
    @FXML
    private void passToMenuWindow(){
        SceneManager.getInstance().loadScene("MenuWindow.fxml");
    }

    /**
     * Replace buttons with label indicating user already voted.
     */
    public void replaceButtonsWithLabel(){
        anchorPane.getChildren().removeAll(name1, name2, name3, name4);
        Label label = new Label();
        label.setLayoutX(200);
        label.setLayoutY(230);
        label.setPrefWidth(300);
        label.setPrefHeight(80);
        label.setWrapText(true);
        label.setText("You already voted!");
        Label remainingtime = new Label();
        remainingtime.setLayoutX(20);
        remainingtime.setLayoutY(300);
        remainingtime.setPrefWidth(550);
        remainingtime.setPrefHeight(200);
        remainingtime.setWrapText(true);
        String time = VotingProcess.getRemainingTime(id);
        remainingtime.setText(time);
        label.setStyle("-fx-padding: 30; -fx-text-fill: black; -fx-font-size: 16pt;");
        remainingtime.setStyle("-fx-padding: 30; -fx-text-fill: black; -fx-font-size: 16pt;");
        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(remainingtime);
    }

    /**
     * Navigate to the feedback window.
     */
    @FXML
    private void passFeedBack(){
        //in this method we are going to the screen to send feedback on some voting
        //this method setting true visibility of text area for name of voting
        SceneManager.getInstance().setSetVisibilityofFeed(true);
        SceneManager.getInstance().loadScene("FeedBackWindow.fxml");
    }

    /**
     * Replace buttons with labels indicating the voting ended.
     *
     * @param buttonId The ID of the button.
     */
    public void replaceButtonsWithLabels(String buttonId) {
        Label label = new Label();
        label.setVisible(false);
        if(buttonId.equals("0")){
            label.setText("This voting ended");
            label.setLayoutX(210);
            label.setLayoutY(220);
            label.setPrefWidth(210);
            label.setPrefHeight(40);
            label.setStyle(" -fx-padding: 10; -fx-text-fill: black; -fx-font-size: 20");
            label.setVisible(true);
        }
        i = -1;
        //creating same labels as buttons
        Label label1 = createLabelFromButton(name1, buttonId);
        Label label2 = createLabelFromButton(name2, buttonId);
        Label label3 = createLabelFromButton(name3, buttonId);
        Label label4 = createLabelFromButton(name4, buttonId);

        //deleteing buttons from anchorpane
        anchorPane.getChildren().removeAll(name1, name2, name3, name4);

        //showing new labels on screen
        anchorPane.getChildren().addAll(label1, label2, label3, label4, label);
    }

    /**
     * Create a labels from a buttons.
     *
     * @param button The button.
     * @param buttonId The ID of the button.
     * @return The created label.
     */
    private Label createLabelFromButton(Button button, String buttonId) {
        i++;
        //creating label that will be exact like button but will contain only results for voting
        Label label = new Label(button.getText());
        label.setLayoutX(button.getLayoutX());
        label.setLayoutY(button.getLayoutY()+30);
        label.setPrefWidth(button.getPrefWidth());
        label.setPrefHeight(button.getPrefHeight());
        label.setAlignment(button.getAlignment());
        label.setId(button.getId());
        label.setWrapText(true);
        if(label.getId().equals(buttonId)){
            label.setText("You voted for: " + button.getText() + ". Also " + VotingProcess.voting(id).get(i) + " people voted for this");
        }
        else{
            label.setText(button.getText() + "\n" + VotingProcess.voting(id).get(i) + " people voted for this");
        }
        label.setStyle("-fx-background-color: #e9e3ce; -fx-padding: 10; -fx-text-fill: black");
        return label;
    }

    /**
     * Show the voting window indicating the voting ended.
     *
     * @param buttonId The ID of the button.
     */
    private void showVoteWindowEnd(String buttonId) {
        replaceButtonsWithLabels(buttonId);
    }
}
