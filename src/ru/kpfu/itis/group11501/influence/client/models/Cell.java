package ru.kpfu.itis.group11501.influence.client.models;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import ru.kpfu.itis.group11501.influence.client.util.helpers.FloatingPoint;

import java.io.IOException;

/**
 * Author: Svintenok Kate and Menshenin Konstantin
 * Date: 11.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Cell {
    private int number;
    private int type;
    private int power;
    private int maxPower;

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

            double scale = maxPower == 8? 1.0 : 1.1;

            cellPane.getChildren().get(1).setScaleX(scale);
            cellPane.getChildren().get(1).setScaleY(scale);

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
        if (type == 1)
            setColor(Color.valueOf("#3A5FCD"));
        else
            setColor(Color.valueOf("#2E8B57"));
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {

        setScale((power - this.power) * 0.025);

        this.power = power;
        setValue(power);
    }

    public void increasePower() {
        setPower(getPower() + 1);
        FloatingPoint.start(cellPane);
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
        return boundsInScene.getMinX() + boundsInScene.getWidth() / 2 + 1.2;
    }

    public double getCenterY () {
        Bounds boundsInScene = cellPane.localToScene(cellPane.getBoundsInLocal());
        return boundsInScene.getMinY() + boundsInScene.getHeight() / 2 + 3.2;
    }


    public void selecting() {
        if (type == 1)
            setColor(Color.valueOf("#6F89D8"));
        else
            setColor(Color.valueOf("#57B27F"));

        setScale(0.025);
    }


    public void deleteSelecting() {
        if (type == 1)
            setColor(Color.valueOf("#3A5FCD"));
        else
            setColor(Color.valueOf("#2E8B57"));

        setScale(-0.025);
    }

    private void setScale(double scaleDifference){
        double scale = cellPane.getChildren().get(0).getScaleX();
        cellPane.getChildren().get(0).setScaleX(scale + scaleDifference);
        cellPane.getChildren().get(0).setScaleY(scale + scaleDifference);
    }

}