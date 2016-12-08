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

/**
 * Created by cmen on 07/12/16.
 */
public class RulesController implements Initializable {

    @FXML
    private Button btnRulesBack;

    public void goToMainMenu(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnRulesBack.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/main_menu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
