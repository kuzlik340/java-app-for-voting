package com.example.uvs.GUI;

import com.example.uvs.DataBase.DataBaseConnection;
import com.example.uvs.Vote_cards.Card;
import com.example.uvs.Voting_logic.VotingProcess;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class VoteWindow implements PassUsername{
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
    private int i = -1;
    List<Card> votingOpt = new ArrayList<>();
    @FXML
    ScrollPane scrollBar;

    @Override
    public void PassUser(String username) {
        this.user = username;
        userName.setText(user);
        alreadyVotedByThisuser = VotingProcess.checkIfVoted(id, user);
        voteEnded = VotingProcess.checkIfVoted3times(id);
        System.out.println("screen = " + alreadyVotedByThisuser);
        if (alreadyVotedByThisuser && !voteEnded){
            System.out.println("alreadyVotedByThisuser");
            replaceButtonsWithLabel();
        }
        else if(voteEnded){
            System.out.println("VoteEnded");
            showVoteWindowEnd("0");
        }
    }
    @FXML
    private void PassToLoginWindow(){
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }

    @FXML
    private void initialize(){
        scrollBar.setVvalue(-1.0);
    }
    public void setID(int ID) {
        this.id = ID;
        System.out.println(id);
    }

    public void setList(List<Card> votingOpt){
       this.votingOpt = votingOpt;
        System.out.println(votingOpt.get(0));
        settingTextFromDB(id);
    }

    public void settingTextFromDB(int id) {
        // Переменные для хранения полученных данных, инициализация значений будет ниже
        for(Card card: votingOpt){
            System.out.println(votingOpt.get(0).getLabel(card));
            if(card.getId(card) == id){
                System.out.println("meow meow");
                mainlabelfromDB = card.getLabel(card);
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
    }


    @FXML
    private void passVote(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();
        showVoteWindowEnd(buttonId);
        DataBaseConnection.DataBaseInterface.userVoted(id, buttonId, user);
    }

    @FXML
    private void passToMenuWindow(){
        SceneManager.getInstance().loadScene("MenuWindow.fxml");
    }
    public void replaceButtonsWithLabel(){
        anchorPane.getChildren().removeAll(name1, name2, name3, name4);
        Label label = new Label();
        label.setLayoutX(30);
        label.setLayoutY(400);
        label.setPrefWidth(500);
        label.setPrefHeight(40);
        label.setWrapText(true);
        label.setText("You already voted!");
        label.setStyle("-fx-background-color: #e9e3ce; -fx-padding: 10; -fx-text-fill: black");
    }

    public void replaceButtonsWithLabels(String buttonId) {
        // Создаем метки с теми же параметрами, что и у кнопок
        Label label1 = createLabelFromButton(name1, buttonId);
        Label label2 = createLabelFromButton(name2, buttonId);
        Label label3 = createLabelFromButton(name3, buttonId);
        Label label4 = createLabelFromButton(name4, buttonId);

        // Удаляем кнопки из AnchorPane
        anchorPane.getChildren().removeAll(name1, name2, name3, name4);

        // Добавляем новые метки в AnchorPane
        anchorPane.getChildren().addAll(label1, label2, label3, label4);
    }

    private Label createLabelFromButton(Button button, String buttonId) {
        i++;
        // Создаем новую метку и устанавливаем те же параметры, что и у кнопки
        Label label = new Label(button.getText());
        label.setLayoutX(button.getLayoutX());
        label.setLayoutY(button.getLayoutY());
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
        // Здесь можно добавить любые другие необходимые стили или параметры
        return label;
    }

    private void showVoteWindowEnd(String buttonId) {
        replaceButtonsWithLabels(buttonId);

    }
}
