package ru.kpfu.itis.group11501.influence.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ru.kpfu.itis.group11501.influence.client.Connection;
import ru.kpfu.itis.group11501.influence.client.helpers.ButtonAnimator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    // FXMLResources
    private static final String GAME_FXML = "../fxml/game.fxml";
    private static final String RULES_FXML = "../fxml/rules.fxml";

    // Buttons from MainMenu
    @FXML
    private Button btnMainMenuRules;
    @FXML
    private Button btnMainMenuPlayGame;
    @FXML
    private Button btnMainMenuExit;


    public void goTo(ActionEvent actionEvent) {

        String resource = "";

        if (actionEvent.getSource().equals(btnMainMenuExit)) {
            System.exit(0);
        }
        else if (actionEvent.getSource().equals(btnMainMenuPlayGame)) {
            resource = GAME_FXML;
            Connection.init();
        }
        else if (actionEvent.getSource().equals(btnMainMenuRules)) {
            resource = RULES_FXML;
        }

        try {
            Stage stage = (Stage) btnMainMenuPlayGame.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(resource));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("MainController initialized!");
        ButtonAnimator.animate(btnMainMenuPlayGame);
        ButtonAnimator.animate(btnMainMenuRules);
        ButtonAnimator.animate(btnMainMenuExit);
    }
}
