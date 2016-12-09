package ru.kpfu.itis.influence.models;

import javafx.fxml.FXMLLoader;
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
        return cell.getLayoutX() + cell.getWidth() / 2;
    }

    public double getCenterY () {
        return cell.getLayoutY() + cell.getHeight() / 2;
    }


}
