package ru.kpfu.itis.influence.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cmen on 07/12/16.
 */
public class GameController implements Initializable {

    // FXMLResources

    private static final String WIN_FXML = "../fxml/win.fxml";
    private static final String LOSE_FXML = "../fxml/lose.fxml";
    private static final String SURRENDER_FXML = "../fxml/surrender.fxml";
    private String resource;

    // Titles

    private static final String WIN_TITLE = "Win";
    private static final String LOSE_TITLE = "Lose";
    private static final String SURRENDER_TITLE = "Surrender";
    private String title;

    // Buttons from game screen
    @FXML
    private Button btnSurrender;
    @FXML
    private Button btnTestWin;
    @FXML
    private Button btnTestLose;

    // Buttons from Win/Lose pop-up windows
    @FXML
    private Button btnWinNotificationOk;
    @FXML
    private Button btnLoseNotificationOk;

    // Buttons from Surrender pop-up window
    @FXML
    private Button btnSurrenderNo;
    @FXML
    private Button btnSurrenderYes;

    public void notification(ActionEvent actionEvent) {

        if (actionEvent.getSource().equals(btnSurrender)) {
            resource = SURRENDER_FXML;
            title = SURRENDER_TITLE;
        }
        if (actionEvent.getSource().equals(btnTestLose)) {
            resource = LOSE_FXML;
            title = LOSE_TITLE;
        }
        if (actionEvent.getSource().equals(btnTestWin)) {
            resource = WIN_FXML;
            title = LOSE_TITLE;
        }

        try {
            Stage modalStage = new Stage();
            Parent modalWindow = FXMLLoader.load(getClass().getResource(resource));
            modalStage.setTitle(title);
            modalStage.setMinWidth(300);
            modalStage.setMinHeight(150);
            modalStage.setResizable(false);
            modalStage.setScene(new Scene(modalWindow));
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            modalStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

/*

    // Next three methods look like the same method with different parameter in getResource() and setTitle() methods
    // I think they may be united into one notification method

    public void winNotification (ActionEvent actionEvent) {
        try {
            Stage modalStage = new Stage();
            Parent modalWindow = FXMLLoader.load(getClass().getResource("../fxml/win.fxml"));
            modalStage.setTitle("Win");
            modalStage.setMinWidth(300);
            modalStage.setMinHeight(150);
            modalStage.setResizable(false);
            modalStage.setScene(new Scene(modalWindow));
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            modalStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loseNotification(ActionEvent actionEvent) {
        try {
            Stage modalStage = new Stage();
            Parent modalWindow = FXMLLoader.load(getClass().getResource("../fxml/lose.fxml"));
            modalStage.setTitle("Lose");
            modalStage.setMinHeight(150);
            modalStage.setMinWidth(300);
            modalStage.setResizable(false);
            modalStage.setScene(new Scene(modalWindow));
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(((Node) actionEvent.getSource()).getScene().getWindow());
            modalStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void surrender(ActionEvent actionEvent) {
        try {
            Stage modalStage = new Stage();
            Parent modalWindow = FXMLLoader.load(getClass().getResource("../fxml/surrender.fxml"));
            modalStage.setTitle("Подтверждение");
            modalStage.setMinWidth(300);
            modalStage.setMinHeight(150);
            modalStage.setResizable(false);
            modalStage.setScene(new Scene(modalWindow));
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(((Node)actionEvent.getSource()).getScene().getWindow());
            modalStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/

    public void winEvent(ActionEvent actionEvent) {
        Stage modalStage = (Stage) btnWinNotificationOk.getScene().getWindow();
        modalStage.close();
    }

    public void loseEvent(ActionEvent actionEvent) {
        Stage modalStage = (Stage) btnLoseNotificationOk.getScene().getWindow();
        modalStage.close();
    }

    public void surrenderRefuse(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSurrenderNo.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
