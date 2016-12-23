package ru.kpfu.itis.group11501.influence.client.util.helpers;

import javafx.animation.*;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;

/**
 * Created by cmen on 12/12/16.
 */
public class ButtonAnimator {

    public static void animate(Button btn) {
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.0);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(colorAdjust.brightnessProperty(), colorAdjust.brightnessProperty().getValue(), Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(1), new KeyValue(colorAdjust.brightnessProperty(), -0.5, Interpolator.LINEAR))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        btn.setOnMouseEntered(mouseEvent -> {
            btn.setEffect(colorAdjust);
            timeline.play();
        });

        btn.setOnMouseExited(mouseEvent -> {
            timeline.stop();
            btn.setEffect(null);
        });
    }
}
