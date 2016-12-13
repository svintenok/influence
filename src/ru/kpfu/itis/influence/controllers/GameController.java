package ru.kpfu.itis.influence.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ru.kpfu.itis.influence.helpers.ButtonAnimator;
import ru.kpfu.itis.influence.models.Cell;
import ru.kpfu.itis.influence.models.GameField;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Created by cmen on 07/12/16.
 */
public class GameController implements Initializable {

    // FXMLResources

    private static final String CELL_FXML = "../fxml/cell.fxml";
    private static final String WIN_FXML = "../fxml/win.fxml";
    private static final String LOSE_FXML = "../fxml/lose.fxml";
    private static final String SURRENDER_FXML = "../fxml/surrender.fxml";
    private String resource;

    // Titles

    private static final String WIN_TITLE = "Win";
    private static final String LOSE_TITLE = "Lose";
    private static final String SURRENDER_TITLE = "Surrender";
    private static String title;

    // Buttons from game screen
    @FXML
    private Button btnSurrender;
    @FXML
    private Button btnTestWin;
    @FXML
    private Button btnTestLose;
    @FXML
    private Button btnGamePlay;

    private static boolean btnGamePlayAnimated = false;

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

    @FXML
    private AnchorPane paneGameField;

    // Наброски для тестинга конструктора gameField
    private static LinkedList<Cell> cells = new LinkedList<>();

    private static double x;
    private static double y;
    private static double shiftX = 0;
    private static double shiftY = 0;

    public void addCell() {

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

        if (actionEvent.getSource().equals(btnSurrender)) {
            resource = SURRENDER_FXML;
            title = SURRENDER_TITLE;
        }
        else if (actionEvent.getSource().equals(btnTestLose)) {
            resource = LOSE_FXML;
            title = LOSE_TITLE;
        }
        else if (actionEvent.getSource().equals(btnTestWin)) {
            resource = WIN_FXML;
            title = LOSE_TITLE;
        }

        try {
            Stage modalStage = new Stage();
            Parent modalWindow = FXMLLoader.load(getClass().getResource(resource));
            modalStage.setTitle(title);
            modalStage.initStyle(StageStyle.UNDECORATED);
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
        if (!btnGamePlayAnimated) {
            ButtonAnimator.animate(btnGamePlay);
            btnGamePlayAnimated = true;
        }
    }
}
