package ru.kpfu.itis.group11501.influence.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ru.kpfu.itis.group11501.influence.client.helpers.*;
import ru.kpfu.itis.group11501.influence.client.guiModels.*;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Created by cmen on 07/12/16.
 */
public class GameController implements Initializable {

    // FXML Resources
    private static final String CELL_FXML = "../fxml/cell.fxml";
    private static final String WIN_FXML = "../fxml/win.fxml";
    private static final String LOSE_FXML = "../fxml/lose.fxml";
    private static final String SURRENDER_FXML = "../fxml/surrender.fxml";
    private String resource;

    // Titles

//    private static final String WIN_TITLE = "Win";
//    private static final String LOSE_TITLE = "Lose";
//    private static final String SURRENDER_TITLE = "Surrender";
//    private static String title;

    // Buttons from game screen
    @FXML
    private Button btnSurrender;
    @FXML
    private Button btnTestWin;
    @FXML
    private Button btnTestLose;
    @FXML
    private Button btnGamePlay;


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

    // Game field
    private GameField gameField;

    // Panes
    @FXML
    private AnchorPane paneGameField;
    @FXML
    private AnchorPane gamePane;

    // Наброски для тестинга конструктора gameField
    private static LinkedList<Cell> cells = new LinkedList<>();

    private static double x;
    private static double y;
    private static double shiftX = 0;
    private static double shiftY = 0;

    public void addCells() {

        gameField = new GameField(9, 14, paneGameField);

        /*
            Cell cell = new Cell();
            cell.setColor(Color.BLUE);
            cell.setValue(12);
            cells.add(cell);
            paneGameField.getChildren().add(cells.getLast().getPaneForm());
        */
    }

    public void notification(ActionEvent actionEvent) {

        double shiftX = 255;
        double shiftY = 200;

        if (actionEvent.getSource().equals(btnSurrender)) {
            shiftX = 175;
            resource = SURRENDER_FXML;
        }
        else if (actionEvent.getSource().equals(btnTestLose)) {
            resource = LOSE_FXML;
        }
        else if (actionEvent.getSource().equals(btnTestWin)) {
            resource = WIN_FXML;
        }

        FXMLLoader.openModalWindow(resource, gamePane, shiftX, shiftY);
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

        System.out.println("GameController initialized.");
        ButtonAnimator.animate(btnGamePlay);

    }
}
