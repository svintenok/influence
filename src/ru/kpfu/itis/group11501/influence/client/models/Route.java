package ru.kpfu.itis.group11501.influence.client.models;

import javafx.geometry.Bounds;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;


/**
 * Author: Svintenok Kate and Konstantin Menshenin
 * Date: 11.12.2016
 * Group: 11-501
 * Project: influence
 */
public class Route {

    private int from;
    private int to;

    private Line edge;
    private Bounds boundsInScene;

    public Route(Cell fromCell, Cell  toCell) {
        this.from = fromCell.getNumber();
        this.to = toCell.getNumber();

        edge = new Line();

        edge.setStrokeWidth(2);
        edge.setStroke(Color.valueOf("#363636"));

        edge.setStartX(fromCell.getCenterX());
        edge.setStartY(fromCell.getCenterY());

        edge.setEndX(toCell.getCenterX());
        edge.setEndY(toCell.getCenterY());

        boundsInScene = edge.localToScene(edge.getBoundsInLocal());
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public Line getEdge() {
        return edge;
    }

    @Override
    public String toString() {
        return "{" + from + ", " + to + '}';
    }


    public double getStartX() {
        return boundsInScene.getMinX();
    }

    public double getStartY() {
        return boundsInScene.getMinY();
    }

    public double getEndX() {
        return boundsInScene.getMaxX();
    }

    public double getEndY() {
        return boundsInScene.getMaxY();
    }
}