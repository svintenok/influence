package ru.kpfu.itis.group11501.influence.client.util.controllers;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import ru.kpfu.itis.group11501.influence.client.models.Cell;
import ru.kpfu.itis.group11501.influence.client.util.Connection;
import ru.kpfu.itis.group11501.influence.client.util.helpers.Loader;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Author: Svintenok Kate and Menshenin Konstantin
 * Date: 25.12.2016
 * Group: 11-501
 * Project: influence
 */
public class WaitingController implements Initializable {

    // FXML Resources
    private static final String GAME_FXML = "../../fxml/game.fxml";

    @FXML
    Pane waitingPane;


    public void toGame() {
        Loader.goTo(GAME_FXML, waitingPane);
    }

    private Cell cellConstructor(String color, int translateX, int translateY, double scale, int toAngle){
        Cell cell = new Cell();
        cell.getCellPane().getChildren().remove(2);


        cell.setColor(Color.valueOf(color));
        cell.getCellPane().setTranslateX(translateX);
        cell.getCellPane().setTranslateY(translateY);
        cell.getCellPane().setScaleX(scale);
        cell.getCellPane().setScaleY(scale);
        cell.getCellPane().getChildren().get(0).setScaleX(1.2);
        cell.getCellPane().getChildren().get(0).setScaleY(1.2);
        cell.getCellPane().getChildren().get(1).setOpacity(1);
        Polygon outerStroke = (Polygon) cell.getCellPane().getChildren().get(1);
        outerStroke.setStrokeWidth(1);
        
        setRotateTransition(cell, toAngle);

        return cell;
    }

    private void setRotateTransition(Cell cell, int toAngle) {
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(7), cell.getCellPane());
        rotateTransition.setAutoReverse(false);
        rotateTransition.setCycleCount(-1);
        rotateTransition.setByAngle(0);
        rotateTransition.setToAngle(toAngle);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Cell blueCell = cellConstructor("#3A5FCD", 325, 218, 3.0, -360);
        Cell greenCell = cellConstructor("#2E8B57", 445, 250, 2.4,  -360);
        Cell redCell = cellConstructor("#FF4040", 375, 325, 1.8, 360);


        waitingPane.getChildren().addAll(blueCell.getCellPane(), greenCell.getCellPane(), redCell.getCellPane());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (Connection.getBufferedInputStream().read() == 2) {

                        System.out.println(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                toGame();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
    }
}
