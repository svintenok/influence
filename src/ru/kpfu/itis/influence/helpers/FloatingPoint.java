package ru.kpfu.itis.influence.helpers;

import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

/**
 * Created by cmen on 23/12/16.
 */
public class FloatingPoint {

    public static void floatIt(Pane cell) {

        Label point = new Label();
        System.out.println("Label created!");
        point.setText("+1");
        point.setTextFill(((Polygon) cell.getChildren().get(0)).getFill());
        point.setTranslateX(cell.getChildren().get(0).getTranslateX() + cell.getWidth() / 3);
        point.setTranslateY(cell.getChildren().get(0).getTranslateY() + cell.getHeight() / 3);
        point.setFont(((Label) cell.getChildren().get(2)).getFont());

        Line path = new Line();
        path.setStartX(cell.getChildren().get(0).getTranslateX() + cell.getWidth() / 3);
        path.setStartY(cell.getChildren().get(0).getTranslateY() + cell.getHeight() / 3);
        path.setEndX(point.getTranslateX());
        path.setEndY(point.getTranslateY() - cell.getHeight() / 3);

        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(point);
        pathTransition.setDuration(Duration.seconds(1));
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(2.0);
        dropShadow.setOffsetX(0.0);
        dropShadow.setOffsetY(0.0);
        dropShadow.setColor(Color.BLACK);

        point.setEffect(dropShadow);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1.2), point);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        cell.getChildren().add(point);

        fadeOut.play();
        pathTransition.play();

    }

}
