package ru.kpfu.itis.influence.models;

import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import ru.kpfu.itis.influence.helpers.FloatingPoint;

import java.io.IOException;

/**
 * Created by cmen on 08/12/16.
 */
public class Cell {

    private static final int MAX_VALUE = 8;
    private static final double PADDING_X = 51.0;
    private static final double PADDING_Y = 61.0;


    private static final String CELL_FXML = "../fxml/cell.fxml";
    private Pane cell;

    private int row;
    private int column;

    public Cell() {
        try {
            cell = FXMLLoader.load(getClass().getResource(CELL_FXML));
            setColor(Color.GRAY);
            cell.getChildren().get(0).setScaleX(0.9);
            cell.getChildren().get(0).setScaleY(0.9);

            cell.getChildren().get(2).setOnMouseClicked(mouseEvent -> {
                Label label = (Label) cell.getChildren().get(2);
                int value = Integer.parseInt(label.getText());
                if (value < MAX_VALUE) {
                    label.setText(String.valueOf(++value));
                    double scaleX = cell.getChildren().get(0).getScaleX();
                    cell.getChildren().get(0).setScaleX(scaleX + 0.025);
                    cell.getChildren().get(0).setScaleY(scaleX + 0.025);

                    FloatingPoint.floatIt(cell);

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPaneForm() {
        return cell;
    }

    public void setColor(Color color) {
        Polygon innerHexagon = (Polygon) cell.getChildren().get(0);
        Polygon outerStroke = (Polygon) cell.getChildren().get(1);
        innerHexagon.setFill(color);
        outerStroke.setStroke(color);
    }

    public void setValue(Integer value) {
        Label label = (Label) cell.getChildren().get(2);
        label.setText(value.toString());
    }

    public double getCenterX() {
        Bounds boundsInScene = cell.localToScene(cell.getBoundsInLocal());
        System.out.println("MinX: " + boundsInScene.getMinX());
        return boundsInScene.getMinX() + boundsInScene.getWidth() / 2 - PADDING_X;
    }

    public double getCenterY () {
        Bounds boundsInScene = cell.localToScene(cell.getBoundsInLocal());
        System.out.println("MinY: " + boundsInScene.getMinY());
        return boundsInScene.getMinY() + boundsInScene.getHeight() / 2 - PADDING_Y;
    }


}
