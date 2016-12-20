package ru.kpfu.itis.group11501.influence.client.models;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.io.IOException;

/**
 * Author: Svintenok Kate and Konstantin Menshenin
 * Date: 11.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Cell {
    private int number;
    private int type;
    private int power;
    private int maxPower;

    public final int maxX = 14;
    public final int maxY = 9;

    private static final double PADDING_X = 51.0;
    private static final double PADDING_Y = 61.0;

    private static final double SHIFT_X = 50;
    private static final double SHIFT_Y = 44;

    private static final String CELL_FXML = "../fxml/cell.fxml";
    private Pane cellPane;

    public Cell(int number, int maxPower, int y, int x) {
        this.number = number;
        this.maxPower = maxPower;
        this.power = 0;
        this.type = 0;

        try {
            cellPane = FXMLLoader.load(getClass().getResource(CELL_FXML));
            setColor(Color.GRAY);
            cellPane.getChildren().get(0).setScaleX(0.9);
            cellPane.getChildren().get(0).setScaleY(0.9);

            cellPane.setLayoutY((y-1)*SHIFT_Y);
            if (y % 2 == 0)
                cellPane.setLayoutX((x-1)*SHIFT_X);
            else
                cellPane.setLayoutX((x-1)*SHIFT_X +SHIFT_X/2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumber() {
        return number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public Pane getCellPane() {
        return cellPane;
    }

    private void setColor(Color color) {
        Polygon innerHexagon = (Polygon) cellPane.getChildren().get(0);
        Polygon outerStroke = (Polygon) cellPane.getChildren().get(1);
        innerHexagon.setFill(color);
        outerStroke.setStroke(color);
    }

    public void setValue(Integer value) {
        Label label = (Label) cellPane.getChildren().get(2);
        label.setText(value.toString());
    }

    public double getCenterX() {
        Bounds boundsInScene = cellPane.localToScene(cellPane.getBoundsInLocal());
        System.out.println("MinX: " + boundsInScene.getMinX());
        return boundsInScene.getMinX() + boundsInScene.getWidth() / 2 - PADDING_X;
    }

    public double getCenterY () {
        Bounds boundsInScene = cellPane.localToScene(cellPane.getBoundsInLocal());
        System.out.println("MinY: " + boundsInScene.getMinY());
        return boundsInScene.getMinY() + boundsInScene.getHeight() / 2 - PADDING_Y;
    }
}