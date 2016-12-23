package ru.kpfu.itis.group11501.influence.client.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import ru.kpfu.itis.group11501.influence.client.Connection;
import ru.kpfu.itis.group11501.influence.client.MovesRecipient;
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

    /*
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
    */


    @FXML
    private AnchorPane gameFieldPane;
    @FXML
    private Pane gamePane;
    @FXML
    private Text gameButtonText;


    private static GameMap gameMap;
    private Status status;
    private MovesRecipient movesRecipient;
    private int pointsQuantity;

    /*
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
    */


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
        for (Cell cell : gameMap.getCells()) {
            gameFieldPane.getChildren().add(cell.getCellPane());
            cell.getCellPane().getChildren().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    cellsHandler(cell);
                }
            });
        }

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


    private void cellsHandler(Cell cell) {
        System.out.println(gameMap.getStatus());
        if (gameMap.getStatus() == Status.POWERS_DISTRIBUTION) {
            if (cell.getType() == gameMap.getOrderNumber() && cell.getPower() < cell.getMaxPower()) {
                if (pointsQuantity > 0) {
                    cell.setPower(cell.getPower() + 1);
                    pointsQuantity--;
                    gameButtonText.setText("Power up your cells (" + pointsQuantity + ")");

                    try {
                        Connection.getBufferedOutputStream().write(cell.getNumber());
                        Connection.getBufferedOutputStream().flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (pointsQuantity == 0)
                        gameButtonText.setText("Click here to end turn");
                }
            }
        }
    }

    public void changeStatus(MouseEvent mouseEvent) {

        System.out.println(gameMap.getStatus());

        if (gameMap.getStatus() == Status.POWERS_DISTRIBUTION) {
            gameMap.setStatus(status.WAITING);
            gameButtonText.setText("Wait for you move");
            pointsQuantity = gameMap.getCellsCountByType(gameMap.getOrderNumber());

            try {
                Connection.getBufferedOutputStream().write(0);
                Connection.getBufferedOutputStream().flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("GameController initialized.");

        try {
            readMap();
            readStaringCells();

            pointsQuantity = gameMap.getCellsCountByType(gameMap.getOrderNumber());

            if (gameMap.getOrderNumber() == 1) {
                gameMap.setStatus(status.POWERS_DISTRIBUTION);
                gameButtonText.setText("Power up your cells (" + pointsQuantity + ")");
            }
            else {
                gameMap.setStatus(status.WAITING);
                gameButtonText.setText("Wait for you move");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        movesRecipient = new MovesRecipient(gameMap, status, gameButtonText);
    }

}
