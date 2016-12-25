package ru.kpfu.itis.group11501.influence.client.util.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import ru.kpfu.itis.group11501.influence.client.util.helpers.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Svintenok Kate and Menshenin Konstantin
 * Date: 07.12.2016
 * Group: 11-501
 * Project: influence
 */
public class RulesController implements Initializable {

    // FXML Resources
    private static final String MENU_FXML = "../../fxml/main_menu.fxml";

    @FXML
    private Pane rulesPane;
    @FXML
    private Button btnRulesBack;

    public void goToMainMenu(ActionEvent actionEvent) {
        Loader.goTo(MENU_FXML, rulesPane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ButtonAnimator.animate(btnRulesBack);
    }
}
