package ru.kpfu.itis.group11501.influence.client.util.helpers;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;

/**
 * Created by cmen on 23/12/16.
 */
public class FloatingPoint {

    public static void start(Pane cellPane) {

        Label pointLabel = new Label();
        pointLabel.setText("+1");
        pointLabel.setTextFill(Color.WHITE);
        pointLabel.setTranslateX(cellPane.getChildren().get(0).getTranslateX() + cellPane.getWidth() / 3);
        pointLabel.setTranslateY(cellPane.getChildren().get(0).getTranslateY() + cellPane.getHeight() / 3);
        pointLabel.setFont(((Label) cellPane.getChildren().get(2)).getFont());

        Line path = new Line();
        path.setStartX(cellPane.getChildren().get(0).getTranslateX() + cellPane.getWidth() / 3);
        path.setStartY(cellPane.getChildren().get(0).getTranslateY() - cellPane.getHeight() / 3);
        path.setEndX(pointLabel.getTranslateX());
        path.setEndY(pointLabel.getTranslateY() - cellPane.getHeight() / 3);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(pointLabel);
        pathTransition.setDuration(Duration.seconds(1));
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(1.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.BLACK);

        pointLabel.setEffect(dropShadow);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.2), pointLabel);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        cellPane.getChildren().add(pointLabel);

        fadeOut.play();
        pathTransition.play();
    }
}
