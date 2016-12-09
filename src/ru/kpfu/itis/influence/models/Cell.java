package ru.kpfu.itis.influence.models;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

/**
 * Created by cmen on 08/12/16.
 */
public class Cell {

    private static final Double PADDING_X = 51.0;
    private static final Double PADDING_Y = 61.0;


    private static final String CELL_FXML = "../fxml/cell.fxml";
    private Pane cell;

    private int row;
    private int column;

    public Cell() {
        try {
            cell = FXMLLoader.load(getClass().getResource(CELL_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Pane getPaneForm() {
        return cell;
    }

    public void setColor(Color color) {
        Polygon innerHexagon = (Polygon) cell.getChildren().get(0);
        Polygon outerBorder = (Polygon) cell.getChildren().get(1);
        innerHexagon.setFill(color);
        outerBorder.setStroke(color);
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
