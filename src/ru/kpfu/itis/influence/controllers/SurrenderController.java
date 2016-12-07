package ru.kpfu.itis.influence.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cmen on 07/12/16.
 */
public class SurrenderController implements Initializable {

    @FXML
    private Button btnNo;
    @FXML
    private Button btnYes;

    public void refuse(ActionEvent actionEvent) {
        Stage stage = (Stage) btnNo.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
