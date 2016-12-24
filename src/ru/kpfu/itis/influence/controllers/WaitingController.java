package ru.kpfu.itis.influence.controllers;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.kpfu.itis.influence.models.Cell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by cmen on 24/12/16.
 */
public class WaitingController implements Initializable {

    @FXML
    Button btnWaiting;

    @FXML
    Pane pane;

    @FXML
    private static final String FXML_GAME = "../fxml/game.fxml";

    public void playGame() {
        try {
            Stage stage = (Stage) btnWaiting.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(FXML_GAME));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Polygon outerStroke;

        Cell blueCell = new Cell();
        blueCell.getPaneForm().getChildren().remove(2);
        blueCell.setColor(Color.rgb(53, 182, 221));
        blueCell.getPaneForm().setTranslateX(325);
        blueCell.getPaneForm().setTranslateY(200);
        blueCell.getPaneForm().setScaleX(3);
        blueCell.getPaneForm().setScaleY(3);
        blueCell.getPaneForm().getChildren().get(0).setScaleX(1.2);
        blueCell.getPaneForm().getChildren().get(0).setScaleY(1.2);
        blueCell.getPaneForm().getChildren().get(1).setOpacity(1);
        outerStroke = (Polygon) blueCell.getPaneForm().getChildren().get(1);
        outerStroke.setStrokeWidth(1);

        RotateTransition rotateTransitionBlue = new RotateTransition(Duration.seconds(7), blueCell.getPaneForm());
        rotateTransitionBlue.setAutoReverse(false);
        rotateTransitionBlue.setCycleCount(-1);
        rotateTransitionBlue.setByAngle(0);
        rotateTransitionBlue.setToAngle(360);
        rotateTransitionBlue.setInterpolator(Interpolator.LINEAR);

        Cell greenCell = new Cell();
        greenCell.getPaneForm().getChildren().remove(2);
        greenCell.setColor(Color.rgb(1, 147, 85));
        greenCell.getPaneForm().setTranslateX(450);
        greenCell.getPaneForm().setTranslateY(250);
        greenCell.getPaneForm().setScaleX(2.4);
        greenCell.getPaneForm().setScaleY(2.4);
        greenCell.getPaneForm().getChildren().get(0).setScaleX(1.2);
        greenCell.getPaneForm().getChildren().get(0).setScaleY(1.2);
        greenCell.getPaneForm().getChildren().get(1).setOpacity(1);
        outerStroke = (Polygon) greenCell.getPaneForm().getChildren().get(1);
        outerStroke.setStrokeWidth(1);

        RotateTransition rotateTransitionGreen = new RotateTransition(Duration.seconds(7), greenCell.getPaneForm());
        rotateTransitionGreen.setAutoReverse(false);
        rotateTransitionGreen.setCycleCount(-1);
        rotateTransitionGreen.setByAngle(0);
        rotateTransitionGreen.setToAngle(-360);
        rotateTransitionGreen.setInterpolator(Interpolator.LINEAR);

        Cell redCell = new Cell();
        redCell.getPaneForm().getChildren().remove(2);
        redCell.setColor(Color.rgb(237, 108, 89));
        redCell.getPaneForm().setTranslateX(375);
        redCell.getPaneForm().setTranslateY(325);
        redCell.getPaneForm().setScaleX(1.8);
        redCell.getPaneForm().setScaleY(1.8);
        redCell.getPaneForm().getChildren().get(0).setScaleX(1.2);
        redCell.getPaneForm().getChildren().get(0).setScaleY(1.2);
        redCell.getPaneForm().getChildren().get(1).setOpacity(1);
        outerStroke = (Polygon) redCell.getPaneForm().getChildren().get(1);
        outerStroke.setStrokeWidth(1);

        RotateTransition rotateTransitionRed = new RotateTransition(Duration.seconds(7), redCell.getPaneForm());
        rotateTransitionRed.setAutoReverse(false);
        rotateTransitionRed.setCycleCount(-1);
        rotateTransitionRed.setByAngle(0);
        rotateTransitionRed.setToAngle(360);
        rotateTransitionRed.setInterpolator(Interpolator.LINEAR);

        rotateTransitionRed.play();
        rotateTransitionBlue.play();
        rotateTransitionGreen.play();

        pane.getChildren().addAll(blueCell.getPaneForm(), greenCell.getPaneForm(), redCell.getPaneForm());

    }
}
