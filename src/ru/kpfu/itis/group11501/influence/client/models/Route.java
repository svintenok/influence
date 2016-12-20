package ru.kpfu.itis.group11501.influence.client.models;

import javafx.geometry.Bounds;
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

    public Route(int from, int to) {
        this.from = from;
        this.to = to;



        //edge.setStrokeWidth(2);
        /*edge.setStartX(fromCell.getCenterX());
        System.out.println("StartX: " + fromCell.getCenterX());
        edge.setStartY(fromCell.getCenterY());
        System.out.println("StartY: " + fromCell.getCenterY());

        edge.setEndX(toCell.getCenterX());
        edge.setEndY(toCell.getCenterY());
        */

        //boundsInScene = edge.localToScene(edge.getBoundsInLocal());
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
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