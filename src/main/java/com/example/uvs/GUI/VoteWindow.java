package com.example.uvs.GUI;

import com.example.uvs.Vote_cards.Card;
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
    List<Card> votingOpt = new ArrayList<>();
    @FXML
    ScrollPane scrollBar;

    @Override
    public void PassUser(String username) {
        this.user = username;
        userName.setText(user);
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
                List<String>options = new ArrayList<>();
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
        // Закрытие ресурсов не требуется, так как используется try-with-resources
    }


    @FXML
    private void passVote(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        showVoteWindowEnd(clickedButton.getId());
    }

    public void replaceButtonsWithLabels() {
        // Создаем метки с теми же параметрами, что и у кнопок
        Label label1 = createLabelFromButton(name1);
        Label label2 = createLabelFromButton(name2);
        Label label3 = createLabelFromButton(name3);
        Label label4 = createLabelFromButton(name4);

        // Удаляем кнопки из AnchorPane
        anchorPane.getChildren().removeAll(name1, name2, name3, name4);

        // Добавляем новые метки в AnchorPane
        anchorPane.getChildren().addAll(label1, label2, label3, label4);
    }

    private Label createLabelFromButton(Button button) {
        // Создаем новую метку и устанавливаем те же параметры, что и у кнопки
        Label label = new Label(button.getText());
        label.setLayoutX(button.getLayoutX());
        label.setLayoutY(button.getLayoutY());
        label.setPrefWidth(button.getPrefWidth());
        label.setPrefHeight(button.getPrefHeight());
        label.setAlignment(button.getAlignment());
        label.setStyle("-fx-background-color: #e9e3ce; -fx-padding: 10; -fx-text-fill: black");
        // Здесь можно добавить любые другие необходимые стили или параметры
        return label;
    }

    private void showVoteWindowEnd(String buttonId) {
        replaceButtonsWithLabels();
    }
}
