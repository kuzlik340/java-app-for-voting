package com.example.uvs.GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import com.example.uvs.Voting_logic.VotingProcess;

import java.io.IOException;

public class VoteWindowEnd implements PassUsername{
    @FXML
    private Label text1, text2, text3, text4;
    @FXML
    private Menu userName;
    private String user;
    @Override
    public void PassUser(String username) {
        this.user = username;
        System.out.println("passssss"+username);
        userName.setText(user);
    }

    @FXML
    private void PassToLoginWindow() {
        SceneManager.getInstance().loadScene("LogInWindow.fxml"); //setting login scene
    }
    public void initData(String buttonId) {
        // Определяем, какое текстовое поле обновить на основе buttonId
        switch(buttonId) {
            case "name1":
                text1.setText("You voted for: Destroy " + VotingProcess.voting());
                text2.setText("Loft-project " + VotingProcess.voting());
                text3.setText("Park " + VotingProcess.voting());
                text4.setText("Museum " + VotingProcess.voting());
                break;
            case "name2":
                text1.setText("Destroy " + VotingProcess.voting());
                text2.setText("You voted for: Loft-project " + VotingProcess.voting());
                text3.setText("Park " + VotingProcess.voting());
                text4.setText("Museum " + VotingProcess.voting());
                break;
            case "name3":
                text1.setText("Destroy " + VotingProcess.voting());
                text2.setText("Loft-project " + VotingProcess.voting());
                text3.setText("You voted for: Park " + VotingProcess.voting());
                text4.setText("Museum " + VotingProcess.voting());
                break;
            case "name4":
                text1.setText("Destroy " + VotingProcess.voting());
                text2.setText("Loft-project " + VotingProcess.voting());
                text3.setText("Park " + VotingProcess.voting());
                text4.setText("You voted for: Museum " + VotingProcess.voting());
                break;
            default:
                System.out.println("Unknown button ID");
                break;
        }

    }
}