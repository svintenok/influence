package ru.kpfu.itis.influence.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private Button btnMainMenuRules;

    @FXML
    private Button btnMainMenuPlayGame;

    @FXML
    private Button btnMainMenuExit;

    public void exitGame(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void goToGame(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnMainMenuPlayGame.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/game.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goToRules(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnMainMenuRules.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/rules.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
