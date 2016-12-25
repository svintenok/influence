package ru.kpfu.itis.group11501.influence.client.util.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ru.kpfu.itis.group11501.influence.client.util.helpers.Loader;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Svintenok Kate and Menshenin Konstantin
 * Date: 24.12.2016
 * Group: 11-501
 * Project: influence
 */
public class EndController implements Initializable {
    // FXML Resources
    private static final String MENU_FXML = "/fxml/main_menu.fxml";

    @FXML
    private Pane endPane;

    public void goToMenu(ActionEvent actionEvent) {
        Stage modalStage = (Stage) endPane.getScene().getWindow();
        modalStage.close();
        Stage stage = (Stage) modalStage.getOwner().getScene().getWindow();
        Loader.goTo(MENU_FXML, stage);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}