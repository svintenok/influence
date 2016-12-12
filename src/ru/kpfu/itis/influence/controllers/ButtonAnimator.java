package ru.kpfu.itis.influence.controllers;

import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * Created by cmen on 12/12/16.
 */
public class ButtonAnimator {

    public static void animate(Button btn) {

        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.0);

        btn.setEffect(colorAdjust);
        btn.setOnMouseEntered(mouseEvent -> {
            Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5),
                        new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)),
                        new KeyFrame(Duration.seconds(1), new KeyValue(colorAdjust.brightnessProperty(), -1, Interpolator.LINEAR))
            );
            timeline.setCycleCount(1);
            timeline.setAutoReverse(false);
            timeline.play();
        });


        /*
        FillTransition fillTransition = new FillTransition(Duration.seconds(0.5), bg);
            this.setOnMouseEntered(mouseEvent -> {
                fillTransition.setFromValue(Color.DARKGRAY);
                fillTransition.setToValue(Color.DARKGOLDENROD);
                fillTransition.setCycleCount(Animation.INDEFINITE);
                fillTransition.setAutoReverse(true);
                fillTransition.play();
            });
            this.setOnMouseExited(mouseEvent -> {
                fillTransition.stop();
                bg.setFill(Color.WHITE);
            });
         */
    }

}
