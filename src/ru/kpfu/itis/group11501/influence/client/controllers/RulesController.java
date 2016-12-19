package ru.kpfu.itis.group11501.influence.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import ru.kpfu.itis.group11501.influence.client.helpers.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cmen on 07/12/16.
 */
public class RulesController implements Initializable {

    // FXML Resources
    private static final String MENU_FXML = "../fxml/main_menu.fxml";

    @FXML
    private Pane rulesPane;
    @FXML
    private Button btnRulesBack;

    public void goToMainMenu(ActionEvent actionEvent) {
        FXMLLoader.goTo(MENU_FXML, rulesPane);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("RulesControlled initialized!");
        ButtonAnimator.animate(btnRulesBack);
    }
}
