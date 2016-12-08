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
