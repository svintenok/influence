package ru.kpfu.itis.group11501.influence.client.util.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import ru.kpfu.itis.group11501.influence.client.models.*;
import ru.kpfu.itis.group11501.influence.client.util.Connection;
import ru.kpfu.itis.group11501.influence.client.util.MovesRecipient;
import ru.kpfu.itis.group11501.influence.client.util.helpers.Loader;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * Author: Svintenok Kate and Menshenin Konstantin
 * Date: 07.12.2016
 * Group: 11-501
 * Project: influence
 */
public class GameController implements Initializable {

    // FXML Resources
    private static final String WIN_FXML = "/fxml/win.fxml";

    /*
    private static final String SURRENDER_FXML = "../fxml/surrender.fxml";
    // Buttons from game screen
    @FXML
    private Button btnSurrender;
    */

    @FXML
    private Pane gameFieldPane;
    @FXML
    private Pane gamePane;


    private GameMap gameMap;
    private int pointsQuantity;
    private Cell selectedCell;
    private Random random;
    private MovesRecipient movesRecipient;
    private GameButton gameButton;

    /*
    public void openSurrenderModalWindow(ActionEvent actionEvent) {

        Loader.openModalWindow(SURRENDER_FXML, gamePane, 175, 200);
    }

    public void surrenderRefuse(ActionEvent actionEvent) {
        Stage stage = (Stage) btnSurrenderNo.getScene().getWindow();
        stage.close();
    }
    */

    private void printMap() throws IOException {

        //cells printing
        for (Cell cell : gameMap.getCells()) {
            gameFieldPane.getChildren().add(cell.getCellPane());
            cell.getCellPane().getChildren().get(2).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        cellsHandler(cell);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            cell.getCellPane().getChildren().get(0).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    try {
                        cellsHandler(cell);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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


    private void cellsHandler(Cell cell) throws IOException {

        if (gameMap.getStatus() == Status.POWERS_DISTRIBUTION) {
            if (cell.getType() == gameMap.getOrderNumber() && cell.getPower() < cell.getMaxPower()) {
                if (pointsQuantity > 0) {
                    cell.increasePower();
                    pointsQuantity--;
                    gameButton.setText("Power up your cells (" + pointsQuantity + ")", "or touch here to end turn");
                    gameButton.updatePowersBalance();

                    try {
                        Connection.getBufferedOutputStream().write(cell.getNumber());
                        Connection.getBufferedOutputStream().flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (pointsQuantity == 0){
                        gameMap.setStatus(Status.WAITING);
                        gameButton.setText("Wait for you move");
                    }
                }
            }
        }

        else if (gameMap.getStatus() == Status.CAPTURE) {

            if (selectedCell == null) {
                if (cell.getType() == gameMap.getOrderNumber()) {
                    cell.selecting();
                    selectedCell = cell;

                    if (cell.getPower() == 1)
                        gameButton.setText("Select a cell with 2+ power", "or touch here to end attack");
                    else
                        gameButton.setText("Touch a nearby cell to attack", "or touch here to end attack");
                }
            }
            else {
                if (cell.getType() == gameMap.getOrderNumber()) {
                    selectedCell.deleteSelecting();
                    selectedCell = cell;
                    cell.selecting();

                    if (cell.getPower() == 1)
                        gameButton.setText("Select a cell with 2+ power", "or touch here to end attack");
                    else
                        gameButton.setText("Touch a nearby cell to attack", "or touch here to end attack");
                }

                else if (selectedCell.getPower() > 1 && gameMap.isConnected(selectedCell, cell)) {

                    Cell toCell = cell;
                    toCell.selecting();

                    if (toCell.getType() == 0){
                        gameMap.changeCell(toCell, gameMap.getOrderNumber(), selectedCell.getPower() - 1);
                        selectedCell.setPower(1);
                    }

                    else {
                        int selectedCellPower = selectedCell.getPower();
                        int toCellPower = toCell.getPower();

                        if (Math.abs(selectedCellPower - toCellPower) >= 2) {
                            toCell.setPower(Math.abs(selectedCellPower - toCellPower) <= toCell.getMaxPower() ? Math.abs(selectedCellPower - toCellPower) : toCell.getMaxPower());
                            selectedCell.setPower(1);
                            if (selectedCellPower > toCellPower) {
                                gameMap.changeCell(toCell, gameMap.getOrderNumber(), toCell.getPower());
                                if (toCell.getMaxPower() < Math.abs(selectedCellPower - toCellPower))
                                    selectedCell.setPower((toCell.getMaxPower() - (selectedCellPower - toCellPower)));
                            }

                        } else {
                            selectedCell.setPower(1);
                            toCell.setPower(1);
                            if (selectedCellPower == toCellPower) {
                                if (random.nextDouble() > 0.5)
                                    gameMap.changeCell(toCell, gameMap.getOrderNumber(), toCell.getPower());
                            }
                            else if (selectedCellPower > toCellPower) {
                                if (random.nextDouble() > 0.25)
                                    gameMap.changeCell(toCell, gameMap.getOrderNumber(), toCell.getPower());
                            }
                            else {
                                if (random.nextDouble() > 0.75)
                                    gameMap.changeCell(toCell, gameMap.getOrderNumber(), toCell.getPower());
                            }
                        }
                    }
                    gameButton.updatePowersBalance();

                    //sending move
                    Connection.getBufferedOutputStream().write(1);
                    Connection.getBufferedOutputStream().flush();

                    Connection.getBufferedOutputStream().write(new byte[]{
                            (byte) selectedCell.getNumber(),
                            (byte) selectedCell.getPower(),
                            (byte) toCell.getNumber(),
                            (byte) toCell.getPower(),
                            (byte) toCell.getType(),
                            });

                    Connection.getBufferedOutputStream().flush();


                    if (gameMap.getCellsCountByType(gameMap.getEnemyOrderNumber()) == 0) {
                        //game ending
                        Connection.getBufferedOutputStream().write(new byte[]{0, 0});
                        Connection.getBufferedOutputStream().flush();
                        movesRecipient.interrupt();
                        Loader.openModalWindow(WIN_FXML, gamePane);
                    }
                    else {

                        selectedCell.deleteSelecting();
                        if (toCell.getType() == gameMap.getOrderNumber()) {
                            selectedCell = toCell;
                            selectedCell.selecting();
                            if (toCell.getPower() == 1)
                                gameButton.setText("Select a cell with 2+ power", "or touch here to end attack");
                        } else {
                            toCell.deleteSelecting();
                            gameButton.setText("Touch a cell of your color", "or touch here to end attack", true);
                        }
                    }
                }
            }

            if (gameMap.getPowersByType(gameMap.getOrderNumber()) == gameMap.getCellsCountByType(gameMap.getOrderNumber()))
                gameButton.setText("Touch here to end attack");
        }
    }

    public void changeStatus() {

        gameButton.animate();

        if (gameMap.getStatus() == Status.CAPTURE){
            if (selectedCell != null){
                selectedCell.deleteSelecting();
                selectedCell = null;
            }
            gameMap.setStatus(Status.POWERS_DISTRIBUTION);
            pointsQuantity = gameMap.getCellsCountByType(gameMap.getOrderNumber());
            gameButton.setText("Power up your cells (" + pointsQuantity + ")", "or touch here to end turn");

            try {
                Connection.getBufferedOutputStream().write(0);
                Connection.getBufferedOutputStream().flush();
                Connection.getBufferedOutputStream().write(pointsQuantity);
                Connection.getBufferedOutputStream().flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if (gameMap.getStatus() == Status.POWERS_DISTRIBUTION) {
            runAutomaticPowersDistribution();
        }
    }

    private void runAutomaticPowersDistribution() {
        while (pointsQuantity > 0) {
            for (Cell cell : gameMap.getCellsByType(gameMap.getOrderNumber())) {
                if (cell.getPower() < cell.getMaxPower()) {

                    cell.increasePower();
                    pointsQuantity--;
                    gameButton.updatePowersBalance();

                    try {
                        Connection.getBufferedOutputStream().write(cell.getNumber());
                        Connection.getBufferedOutputStream().flush();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (pointsQuantity == 0) {
                        gameMap.setStatus(Status.WAITING);
                        gameButton.setText("Wait for you move");
                    }
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            gameMap = Connection.getGameMap();
            printMap();

            gameButton = new GameButton(gameMap);
            gamePane.getChildren().add(gameButton.getGameButtonPane());
            gameButton.getGameButtonPane().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    changeStatus();
                }
            });

            pointsQuantity = gameMap.getCellsCountByType(gameMap.getOrderNumber());

            if (gameMap.getOrderNumber() == 1) {
                gameMap.setStatus(Status.CAPTURE);
                gameButton.setText("Touch a cell of your color", "or touch here to end attack", true);

            } else {
                gameMap.setStatus(Status.WAITING);
                gameButton.setText("Wait for you move");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        random = new Random();
        movesRecipient = new MovesRecipient(gameMap, gameButton, gamePane);

    }

}
