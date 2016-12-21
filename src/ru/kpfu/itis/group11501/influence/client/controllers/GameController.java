package ru.kpfu.itis.group11501.influence.client.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import ru.kpfu.itis.group11501.influence.client.Connection;
import ru.kpfu.itis.group11501.influence.client.helpers.*;
import ru.kpfu.itis.group11501.influence.client.models.*;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cmen on 07/12/16.
 */
public class GameController implements Initializable {

    // FXML Resources
    private static final String WIN_FXML = "../fxml/win.fxml";
    private static final String LOSE_FXML = "../fxml/lose.fxml";
    private static final String SURRENDER_FXML = "../fxml/surrender.fxml";
    private String resource;

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

    // Panes
    @FXML
    private AnchorPane gameFieldPane;
    @FXML
    private AnchorPane gamePane;

    private static GameMap gameMap;


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
        Loader.openModalWindow(resource, gamePane, shiftX, shiftY);
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


    private void readMap() throws IOException {
        //cells reading
        byte[] cells = new byte[Connection.getBufferedInputStream().read()];
        Connection.getBufferedInputStream().read(cells);

        //routes reading
        byte[] routesArraySize = new byte[2];
        Connection.getBufferedInputStream().read(routesArraySize);
        byte[] routes = new byte[new BigInteger(routesArraySize).intValue()];
        Connection.getBufferedInputStream().read(routes);

        //logs
        for (int i = 0; i < cells.length; i++)
            System.out.print(cells[i]  + " ");
        System.out.println();

        //logs
        for (int i = 0; i < routes.length; i++)
            System.out.print(routes[i]  + " ");
        System.out.println();

        gameMap = new GameMap(cells, routes);
        gameMap.setOrderNumber(Connection.getBufferedInputStream().read());

        //logs
        gameMap.printGameMap();


        //cells printing
        for (Cell cell : gameMap.getCells())
            gameFieldPane.getChildren().add(cell.getCellPane());

        //routes printing
        for (Route route : gameMap.getRoutes()) {
            Line edge = route.getEdge();
            gameFieldPane.getChildren().add(edge);
            edge.toBack();
        }

    }

    private void readStaringCells() throws IOException {
        Cell cell = gameMap.getCell(Connection.getBufferedInputStream().read());
        gameMap.changeCell(cell, 1, 2);

        cell = gameMap.getCell(Connection.getBufferedInputStream().read());
        gameMap.changeCell(cell, 2, 3);;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("GameController initialized.");
        if (btnGamePlay != null)
            ButtonAnimator.animate(btnGamePlay);

        if (gameMap == null) {
            try {
                readMap();
                readStaringCells();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
