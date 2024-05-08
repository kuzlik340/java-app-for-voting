package com.example.uvs.GUI;

import com.example.uvs.Citizen.UserSession;
import com.example.uvs.Vote_cards.Card;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;

/**
 * The CreatingWindow class controls the interface for creating new voting cards.
 */
public class CreatingWindow {
    @FXML
    TextField title, text, option1, option2, option3, option4;
    @FXML
    private Menu userName;

    /**
     * Method if user want to log out.
     */
    @FXML
    private void PassToLoginWindow(){
        UserSession.getInstance().setStarted(null, null);
        LogInController.serializeSession(UserSession.getInstance());
        SceneManager.getInstance().loadScene("LogInWindow.fxml");
    }


    @FXML
    public void initialize(){
        userName.setText(UserSession.getInstance().getLogin());
    }

    /**
     * Navigate to the menu window.
     */
    @FXML
    private void passToMenuWindow(){
        SceneManager.getInstance().loadScene("MenuWindow.fxml");
    }

    /**
     * Creation of a new voting card.
     */
    @FXML
    public void Create() {
        String titleInput = title.getText();
        String textInput = text.getText();
        String option1Input = option1.getText();
        String option2Input = option2.getText();
        String option3Input = option3.getText();
        String option4Input = option4.getText();
        Card.CreateCards(titleInput, textInput, option1Input, option2Input, option3Input, option4Input);
        title.clear();
        text.clear();
        option1.clear();
        option2.clear();
        option3.clear();
        option4.clear();
    }
}
